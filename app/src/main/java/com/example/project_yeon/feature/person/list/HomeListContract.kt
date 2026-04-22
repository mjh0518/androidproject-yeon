package com.example.project_yeon.feature.person.list

import com.example.project_yeon.domain.person.model.PersonListItem

enum class SortType(
    val label: String
) {
    NEWEST("최신 추가순"),
    OLDEST("오래된 순"),
    CLOSENESS_DESC("친밀도 높은 순"),
    NAME_ASC("이름 순")
}


data class HomeListState(
    val isLoading: Boolean = true,
    val persons: List<PersonListItem> = emptyList(),
    val expandedPersonId: Long? = null,
    val pinnedPersonIds : Set<Long> = emptySet(),
    val errorMessage: String? = null,

    val isSearchMode : Boolean = false,
    val searchQuery : String = "",
    val searchItems : List<PersonListItem> = emptyList(),
    val isSearchResultEmpty : Boolean = false,

    val isSortMenuVisible : Boolean = false,
    val currentSortType: SortType = SortType.NEWEST,

) {
    val isEmpty: Boolean
        get() = !isLoading && persons.isEmpty()
}

sealed interface HomeListEvent {
    data object OnAddClick : HomeListEvent
    data class OnExpandClick(val personId: Long) : HomeListEvent
    data class OnMoreDetailClick(val personId: Long) : HomeListEvent
    data class OnPinClick(val personId: Long) : HomeListEvent

    data object OnSearchIconButtonClicked : HomeListEvent
    data class OnSearchQueryChanged(val query : String) : HomeListEvent
    data object OnSearchClearClicked : HomeListEvent
    data object OnSearchCloseClicked : HomeListEvent

    data object OnSortIconClicked : HomeListEvent
    data class OnSortTypeSelected(val sortType: SortType) : HomeListEvent
    data object OnSortMenuDismissed : HomeListEvent
}

sealed interface HomeListEffect {
    data object NavigateToAdd : HomeListEffect
    data class NavigateToDetail(val personId: Long) : HomeListEffect
}

