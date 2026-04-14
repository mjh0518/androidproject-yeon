package com.example.project_yeon.app.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.project_yeon.feature.person.splash.SplashScreen
import com.example.project_yeon.feature.person.add.AddPersonScreen
import com.example.project_yeon.feature.person.detail.DetailPersonScreen
import com.example.project_yeon.feature.person.list.HomeListScreen
import com.example.project_yeon.feature.person.modify.ModifyPersonScreen
import com.example.project_yeon.feature.person.trash.TrashScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = AppRoute.Splash.route,
        modifier = modifier
    ) {
        composable(AppRoute.Splash.route) {
            SplashScreen(
                onNavigateToHome = {
                    navController.navigate(AppRoute.HomeList.route) {
                        popUpTo(AppRoute.Splash.route) { inclusive = true }
                    }
                }
            )
        }

        composable(AppRoute.HomeList.route) {
            HomeListScreen(
                navController = navController,
            )
        }

        composable(AppRoute.AddPerson.route) {
            AddPersonScreen(
                navController = navController,
            )
        }

        composable(
            route = AppRoute.DetailPerson.route,
            arguments = listOf(
                navArgument("personId") {
                    type = NavType.LongType
                }
            )
        ) { backStackEntry ->
            val personId = backStackEntry.arguments?.getLong("personId") ?: return@composable

            DetailPersonScreen(
                personId = personId,
                onBackClick = {
                    navController.popBackStack()
                },
                onNavigateToModify = { id ->
                    navController.navigate(AppRoute.ModifyPerson.createRoute(id))
                }
            )
        }

        composable(
            route = AppRoute.ModifyPerson.route,
            arguments = listOf(
                navArgument("personId") {
                    type = NavType.LongType
                }
            )
        ) { backStackEntry ->
            val personId = backStackEntry.arguments?.getLong("personId") ?: return@composable

            ModifyPersonScreen(
                personId = personId,
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }

        composable(AppRoute.Trash.route) {
            TrashScreen(
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}