package com.example.jobsearchproject.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.jobsearchproject.view.EmptyScreen
import com.example.jobsearchproject.view.favoritesScreen.FavoritesScreen
import com.example.jobsearchproject.view.jobsPageScreen.JobsPageScreen
import com.example.jobsearchproject.view.loginOneScreen.LoginScreenOneActivity
import com.example.jobsearchproject.view.loginTwoScreen.LoginScreenTwoActivity
import com.example.jobsearchproject.view.mainScreen.MainScreen
import com.example.jobsearchproject.view.mainScreen.viewmodel.MainVM

@Composable
@Preview(showBackground = true)
fun PreviewNavigation() {
    //NavigationHost()
}

@Composable
fun NavigationHost(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    mainVM: MainVM,

    ) {
    val currentRoute = navHostController.currentBackStackEntry

    NavHost(
        modifier = modifier,
        navController = navHostController,
        startDestination = RouteNavigation.LOGIN_ONE_ACTIVITY.route
    ) {
        composable(RouteNavigation.LOGIN_ONE_ACTIVITY.route) {
            LoginScreenOneActivity(
                navigation = {
                    navHostController.navigate(RouteNavigation.LOGIN_TWO_ACTIVITY.route + "?$it") {
                        launchSingleTop = true
                    }
                }
            )
        }

        composable(
            RouteNavigation.LOGIN_TWO_ACTIVITY.route + "?{email}",
            arguments = listOf(
                navArgument("email") { type = NavType.StringType }
            )
        ) { argument ->
            val userEmail = argument.arguments?.getString("email") ?: ""
            LoginScreenTwoActivity(emailUser = userEmail) {
                navHostController.navigate(RouteNavigation.MAIN_ACTIVITY.route) {
                    popUpTo(RouteNavigation.LOGIN_ONE_ACTIVITY.route)
                    launchSingleTop = true
                }
            }
        }

        composable(RouteNavigation.MAIN_ACTIVITY.route) {
            MainScreen(mainVM) {
                navHostController.navigate(RouteNavigation.JOBS_PAGE_ACTIVITY.route) {
                    launchSingleTop = true
                }
            }
        }
        composable(RouteNavigation.JOBS_PAGE_ACTIVITY.route) {
            JobsPageScreen(mainVM = mainVM) {
                navHostController.popBackStack()
            }
        }
        composable(RouteNavigation.EMPTY_ACTIVITY.route){
            EmptyScreen()
        }
        composable(RouteNavigation.FAVORITES_ACTIVITY.route) {
            FavoritesScreen(
                getTextNumberVacancy = {
                    mainVM.getVacanciesText(it)
                },
                getTextData = {
                    mainVM.formatDate(it)
                },
                onClickFavorites = {
                    mainVM.updateIsFavorites(it)
                },
                getTextLookingNumber = {
                    mainVM.getLookingText(it)
                },
                viewVacancy = {
                    mainVM.viewVacancy(it)
                },
                navigation = {
                    navHostController.navigate(RouteNavigation.JOBS_PAGE_ACTIVITY.route) {
                        launchSingleTop = true
                    }
                }
            )
        }
    }
}