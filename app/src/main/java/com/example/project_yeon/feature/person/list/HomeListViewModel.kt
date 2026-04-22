package com.example.project_yeon.feature.person.list

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project_yeon.domain.person.model.PersonListItem
import com.example.project_yeon.domain.person.usecase.ObservePersonListUseCase
import com.example.project_yeon.domain.person.usecase.UpdatePinnedStateUseCase
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
    private val observePersonListUseCase: ObservePersonListUseCase,
    private val updatePinnedStateUseCase: UpdatePinnedStateUseCase
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
                    _uiState.update { currentState ->
                        val displayPersons = buildDisplayPersons(
                            persons = personList,
                            sortType = currentState.currentSortType
                        )

                        val normalizedQuery = currentState.searchQuery.trim()

                        val updatedSearchItems =
                            if (currentState.isSearchMode) {
                                if (normalizedQuery.isBlank()) {
                                    displayPersons
                                } else {
                                    displayPersons.filter { item ->
                                        item.name.contains(normalizedQuery, ignoreCase = true)
                                    }
                                }
                            } else {
                                emptyList()
                            }

                        currentState.copy(
                            isLoading = false,
                            persons = displayPersons,
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
                viewModelScope.launch {
                    val target = _uiState.value.persons.firstOrNull { it.personId == event.personId }
                        ?: return@launch

                    val nextPinned = !target.isPinned
                    val nextPinnedAt = if (nextPinned) System.currentTimeMillis() else null
                    updatePinnedStateUseCase(
                        event.personId,
                        nextPinned,
                        nextPinnedAt
                    )
                }
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

            is HomeListEvent.OnSortIconClicked -> {
                _uiState.update {
                    it.copy(
                        isSortMenuVisible = true
                    )
                }
            }

            is HomeListEvent.OnSortTypeSelected -> {
                _uiState.update { currentState ->
                    val displayPersons = buildDisplayPersons(
                        persons = currentState.persons,
                        sortType = event.sortType
                    )

                    val normalizedQuery = currentState.searchQuery.trim()

                    val updatedSearchItems =
                        if (currentState.isSearchMode) {
                            if (normalizedQuery.isBlank()) {
                                displayPersons
                            } else {
                                displayPersons.filter { item ->
                                    item.name.contains(normalizedQuery, ignoreCase = true)
                                }
                            }
                        } else {
                            emptyList()
                        }

                    currentState.copy(
                        currentSortType = event.sortType,
                        isSortMenuVisible = false,
                        persons = displayPersons,
                        searchItems = updatedSearchItems,
                        isSearchResultEmpty = currentState.isSearchMode &&
                                normalizedQuery.isNotBlank() &&
                                updatedSearchItems.isEmpty()
                    )
                }
            }

            is HomeListEvent.OnSortMenuDismissed -> {
                _uiState.update {
                    it.copy(
                        isSortMenuVisible = false
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

    private fun sortPersons(
        persons : List<PersonListItem>,
        sortType: SortType
    ) : List<PersonListItem> {
        Log.d("HomeListSort", "sortPersons called, sortType=$sortType, size=${persons.size}")
        return when(sortType){
            SortType.NEWEST -> persons.sortedByDescending { it.createdAt}
            SortType.OLDEST -> persons.sortedBy { it.createdAt }
            SortType.CLOSENESS_DESC -> persons.sortedByDescending { it.intimacy }
            SortType.NAME_ASC -> persons.sortedBy { it.name.lowercase() }
        }
    }
    private fun buildDisplayPersons(
        persons: List<PersonListItem>,
        sortType: SortType
    ): List<PersonListItem> {
        val pinnedPersons = persons
            .filter { it.isPinned }
            .sortedByDescending { it.pinnedAt ?: Long.MIN_VALUE }

        val normalPersons = persons
            .filterNot { it.isPinned }
            .let { nonPinned ->
                when (sortType) {
                    SortType.NEWEST -> nonPinned.sortedByDescending { it.createdAt }
                    SortType.OLDEST -> nonPinned.sortedBy { it.createdAt }
                    SortType.CLOSENESS_DESC -> nonPinned.sortedByDescending { it.intimacy }
                    SortType.NAME_ASC -> nonPinned.sortedBy { it.name.lowercase() }
                }
            }

        return pinnedPersons + normalPersons
    }
}