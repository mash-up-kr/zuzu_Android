package com.mashup.zuzu.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.navigation.material.bottomSheet
import com.mashup.zuzu.ZuzuAppState
import com.mashup.zuzu.ui.category.CategoryRoute
import com.mashup.zuzu.ui.home.HomeRoute
import com.mashup.zuzu.ui.leave.LeaveUiEventState
import com.mashup.zuzu.ui.leave.LeaveRoute
import com.mashup.zuzu.ui.user.SettingRoute
import com.mashup.zuzu.ui.user.UpdateProfileUiEventState
import com.mashup.zuzu.ui.user.UserRoute
import com.mashup.zuzu.ui.user.edit.EditUserProfileRoute

/**
 * @Created by 김현국 2022/07/23
 * @Time 3:57 오후
 */

internal fun NavGraphBuilder.homeGraph(
    appState: ZuzuAppState
) {
    navigation(
        route = NavigationRoute.HomeScreenGraph.route,
        startDestination = NavigationRoute.HomeScreenGraph.HomeScreen.route
    ) {
        composable(
            route = NavigationRoute.HomeScreenGraph.HomeScreen.route
        ) {
            HomeRoute(
                onCategoryClick = { category ->
                    appState.navigateRoute(NavigationRoute.CategoryScreenGraph.CategoryScreen.route + "/${category.title}")
                },
                onWorldCupItemClick = {},
                onWineBoardClick = {}
            )
        }
    }
}
internal fun NavGraphBuilder.categoryGraph(
    appState: ZuzuAppState
) {
    navigation(
        route = NavigationRoute.CategoryScreenGraph.route,
        startDestination = NavigationRoute.CategoryScreenGraph.CategoryScreen.route
    ) {
        composable(
            route = NavigationRoute.CategoryScreenGraph.CategoryScreen.route + "/{category}"
        ) {
            CategoryRoute(
                category = it.arguments?.getString("category")!!,
                onWineBoardClick = {},
                onBackButtonClick = {
                    appState.navigateBackStack()
                }
            )
        }
    }
}
@OptIn(ExperimentalMaterialNavigationApi::class)
internal fun NavGraphBuilder.userGraph(
    appState: ZuzuAppState
) {
    navigation(
        route = NavigationRoute.UserScreenGraph.route,
        startDestination = NavigationRoute.UserScreenGraph.UserScreen.route
    ) {
        composable(
            route = NavigationRoute.UserScreenGraph.UserScreen.route
        ) {
            UserRoute(
                onSettingClick = {
                    appState.navigateRoute(NavigationRoute.UserScreenGraph.SettingScreen.route)
                },
                onEditButtonClick = {
                    appState.navigateRoute(NavigationRoute.UserScreenGraph.EditUserProfileBottomSheet.route)
                },
                onWorldCupItemClick = {},
                onWineClick = {}
            )
        }
        composable(
            route = NavigationRoute.UserScreenGraph.SettingScreen.route
        ) {
            SettingRoute(
                onBackButtonClick = {
                    appState.navigateBackStack()
                },
                onLeaveButtonClick = {
                    appState.navigateRoute(NavigationRoute.UserScreenGraph.LeaveScreen.route)
                },
                onEditButtonClick = {
                    appState.navigateRoute(NavigationRoute.UserScreenGraph.EditUserProfileBottomSheet.route)
                }
            )
        }
        composable(
            route = NavigationRoute.UserScreenGraph.LeaveScreen.route
        ) {
            LeaveRoute(
                onBackButtonClick = {
                    appState.navigateBackStack()
                },
                onKeepUsingButtonClick = {
                    appState.navigateBackStack()
                },
                onLeaveState = { leaveUiEventState ->
                    when (leaveUiEventState) {
                        is LeaveUiEventState.Success -> {
                            appState.showProgressBar()
                            appState.navigateBackStack()
                        }
                        is LeaveUiEventState.Loading -> {
                            appState.showProgressBar()
                        }
                    }
                }
            )
        }
        bottomSheet(
            route = NavigationRoute.UserScreenGraph.EditUserProfileBottomSheet.route
        ) {
            EditUserProfileRoute(
                onSubmitState = { updateProfileUiEventState ->
                    when (updateProfileUiEventState) {
                        is UpdateProfileUiEventState.Success -> {
                            appState.showProgressBar()
                            appState.navigateBackStack()
                        }
                        is UpdateProfileUiEventState.Loading -> {
                            appState.showProgressBar()
                        }
                    }
                }
            )
        }
    }
}
