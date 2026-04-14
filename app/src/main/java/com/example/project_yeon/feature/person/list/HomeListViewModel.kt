package com.example.project_yeon.feature.person.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project_yeon.domain.person.usecase.ObservePersonListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
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



    init{
        observePersons()
    }

    private fun observePersons(){
        viewModelScope.launch {
            observePersonListUseCase()
                .catch {
                    _uiState.update { currentState ->
                        currentState.copy(
                            isLoading = false,
                            errorMessage = "목록을 불러오지 못했습니다."
                        )
                    }
                }
                .collect{ personList ->
                    _uiState.update{ currentState ->
                        currentState.copy(
                            isLoading = false,
                            persons = personList,
                            errorMessage = "목록을 불러오지 못했습니다."
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

            is HomeListEvent.OnMoreDetailClick -> {
                viewModelScope.launch {
                    _effect.emit(HomeListEffect.NavigateToDetail(event.personId))
                }
            }
        }
    }
}