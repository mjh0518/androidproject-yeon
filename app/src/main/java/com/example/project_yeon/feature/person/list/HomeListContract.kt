package com.example.project_yeon.feature.person.list

import com.example.project_yeon.domain.person.model.PersonListItem


data class HomeListState(
    val isLoading: Boolean = true,
    val persons: List<PersonListItem> = emptyList(),
    val expandedPersonId: Long? = null,
    val pinnedPersonIds : Set<Long> = emptySet(),
    val errorMessage: String? = null,
) {
    val isEmpty: Boolean
        get() = !isLoading && persons.isEmpty()
}

sealed interface HomeListEvent {
    data object OnAddClick : HomeListEvent
    data class OnExpandClick(val personId: Long) : HomeListEvent
    data class OnMoreDetailClick(val personId: Long) : HomeListEvent
    data class OnPinClick(val personId: Long) : HomeListEvent
}

sealed interface HomeListEffect {
    data object NavigateToAdd : HomeListEffect
    data class NavigateToDetail(val personId: Long) : HomeListEffect
}

