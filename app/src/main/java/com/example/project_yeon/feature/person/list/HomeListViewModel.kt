package com.example.project_yeon.feature.person.list

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project_yeon.domain.person.usecase.ObservePersonListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class HomeListViewModel @Inject constructor(
    private val observePersonListUseCase: ObservePersonListUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(HomeListState())
    val uiState : StateFlow<HomeListState> = _uiState.asStateFlow()
    private val _effect = MutableSharedFlow<HomeListEffect>()
    val effect = _effect.asSharedFlow()

    private var observePersonsJob: Job? = null

    init{
        observePersons()
    }

    private fun observePersons() {
        observePersonsJob?.cancel()
        observePersonsJob = viewModelScope.launch {
            Log.d("HomeListVM", "observePersons start")

            observePersonListUseCase()
                .catch { throwable ->
                    Log.d(
                        "HomeListVM",
                        "error type=${throwable::class.java.simpleName}, message=${throwable.message}"
                    )

                    _uiState.update { currentState ->
                        currentState.copy(
                            isLoading = false,
                            errorMessage = throwable.message ?: "목록을 불러오지 못했습니다."
                        )
                    }
                }
                .collect { personList ->
                    Log.d("HomeListVM", "collect success, size=${personList.size}")

                    _uiState.update { currentState ->
                        val normalizedQuery = currentState.searchQuery.trim()

                        val updatedSearchItems =
                            if (currentState.isSearchMode) {
                                if (normalizedQuery.isBlank()) {
                                    personList
                                } else {
                                    personList.filter { item ->
                                        item.name.contains(normalizedQuery, ignoreCase = true)
                                    }
                                }
                            } else {
                                emptyList()
                            }

                        currentState.copy(
                            isLoading = false,
                            persons = personList,
                            searchItems = updatedSearchItems,
                            isSearchResultEmpty = currentState.isSearchMode &&
                                    normalizedQuery.isNotBlank() &&
                                    updatedSearchItems.isEmpty(),
                            errorMessage = null
                        )
                    }
                }
        }
    }

    fun onEvent(event : HomeListEvent) {
        when (event) {
            is HomeListEvent.OnAddClick -> {
                viewModelScope.launch {
                    _effect.emit(HomeListEffect.NavigateToAdd)
                }
            }
            is HomeListEvent.OnExpandClick -> {
                _uiState.update { currentState ->
                    currentState.copy(
                        expandedPersonId =
                            if (currentState.expandedPersonId == event.personId) null
                            else event.personId
                    )
                }
            }
            is HomeListEvent.OnPinClick -> {
                _uiState.update { currentState ->
                    val updatedPinnedIds =
                        if (currentState.pinnedPersonIds.contains(event.personId)) {
                            currentState.pinnedPersonIds - event.personId
                        } else {
                            currentState.pinnedPersonIds + event.personId
                        }

                    currentState.copy(
                        pinnedPersonIds = updatedPinnedIds
                    )
                }
                //TODO: 고정 기능 구현
            }

            is HomeListEvent.OnMoreDetailClick -> {
                viewModelScope.launch {
                    _effect.emit(HomeListEffect.NavigateToDetail(event.personId))
                }
            }
            is HomeListEvent.OnSearchIconButtonClicked -> {
                _uiState.update {
                    if (it.isSearchMode) {
                        it.copy(
                            isSearchMode = false,
                            searchQuery = "",
                            searchItems = emptyList(),
                            isSearchResultEmpty = false,
                            expandedPersonId = null
                        )
                    } else {
                        it.copy(
                            isSearchMode = true,
                            searchQuery = "",
                            searchItems = it.persons,
                            isSearchResultEmpty = false,
                            expandedPersonId = null
                        )
                    }
                }
            }
            is HomeListEvent.OnSearchQueryChanged -> {
                updateSearchResults(event.query)
            }
            is HomeListEvent.OnSearchClearClicked -> {
                _uiState.update {
                    it.copy(
                        searchQuery = "",
                        searchItems = it.persons,
                        isSearchResultEmpty = false
                    )
                }
            }
            is HomeListEvent.OnSearchCloseClicked -> {
                _uiState.update {
                    it.copy(
                        isSearchMode = false,
                        searchQuery = "",
                        searchItems = emptyList(),
                        isSearchResultEmpty = false,
                        expandedPersonId = null
                    )
                }
            }
        }
    }

    fun retryObservePersons() {
        _uiState.update { currentState ->
            currentState.copy(
                isLoading = true,
                errorMessage = null
            )
        }
        observePersons()
    }
    private fun updateSearchResults(query: String) {
        val source = _uiState.value.persons

        val result = if (query.isBlank()) {
            source
        } else {
            source.filter { item ->
                item.name.contains(query.trim(), ignoreCase = true)
            }
        }

        _uiState.update {
            it.copy(
                searchQuery = query,
                searchItems = result,
                isSearchResultEmpty = query.isNotBlank() && result.isEmpty()
            )
        }
    }
}