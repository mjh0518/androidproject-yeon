package com.example.project_yeon.app.navigation

sealed class AppRoute(val route: String) {
    data object Splash : AppRoute("splash")
    data object HomeList : AppRoute("home_list")
    data object AddPerson : AppRoute("add_person")
    data object DetailPerson : AppRoute("detail_person/{personId}") {
        fun createRoute(personId: Long): String = "detail_person/$personId"
    }
    data object ModifyPerson : AppRoute("modify_person/{personId}") {
        fun createRoute(personId: Long): String = "modify_person/$personId"
    }
    data object Trash : AppRoute("trash")
}