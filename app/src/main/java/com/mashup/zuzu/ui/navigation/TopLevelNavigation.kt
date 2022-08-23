package com.mashup.zuzu.ui.navigation

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.navigation.material.bottomSheet
import com.mashup.zuzu.ZuzuAppState
import com.mashup.zuzu.findStartDestination
import com.mashup.zuzu.ui.category.CategoryRoute
import com.mashup.zuzu.ui.category.CategoryUiEvents
import com.mashup.zuzu.ui.category.CategoryViewModel
import com.mashup.zuzu.ui.home.HomeRoute
import com.mashup.zuzu.ui.home.HomeUiEvents
import com.mashup.zuzu.ui.home.HomeViewModel
import com.mashup.zuzu.ui.leave.LeaveRoute
import com.mashup.zuzu.ui.leave.LeaveUiEvents
import com.mashup.zuzu.ui.leave.LeaveUiState
import com.mashup.zuzu.ui.leave.LeaveViewModel
import com.mashup.zuzu.ui.review.ReviewDetailRoute
import com.mashup.zuzu.ui.review.ReviewShareCardRoute
import com.mashup.zuzu.ui.review.ReviewWriteRoute
import com.mashup.zuzu.ui.setting.SettingRoute
import com.mashup.zuzu.ui.setting.SettingUiEvents
import com.mashup.zuzu.ui.setting.SettingViewModel
import com.mashup.zuzu.ui.user.UpdateProfileUiEventState
import com.mashup.zuzu.ui.user.UserRoute
import com.mashup.zuzu.ui.user.UserUiEvents
import com.mashup.zuzu.ui.user.UserViewModel
import com.mashup.zuzu.ui.user.edit.EditUserProfileRoute
import com.mashup.zuzu.ui.user.review.UserReviewDetailRoute
import com.mashup.zuzu.ui.user.review.UserReviewDetailUiEvents
import com.mashup.zuzu.ui.user.review.UserReviewDetailViewModel
import timber.log.Timber

/**
 * @Created by 김현국 2022/07/23
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
            val viewModel: HomeViewModel = hiltViewModel()
            HomeRoute(
                viewModel = viewModel,
                onClick = { homeUiEvents ->
                    when (homeUiEvents) {
                        is HomeUiEvents.CategoryClick -> {
                            appState.putCategoryList(category = homeUiEvents.categoryList)
                            appState.navigateRoute(route = NavigationRoute.CategoryScreenGraph.CategoryScreen.route + "/${homeUiEvents.category.title}")
                        }
                        is HomeUiEvents.WineBoardClick -> {
                            appState.navigateRoute(route = NavigationRoute.ReviewGraph.ReviewDetailScreen.route + "/${homeUiEvents.wine.id}")
                        }
                        is HomeUiEvents.WorldCupItemClick -> {
                        }
                        is HomeUiEvents.RefreshButtonClick -> {
                            viewModel.getRandomWine(context = homeUiEvents.context)
                        }
                    }
                }
            )
        }
        composable(
            route = NavigationRoute.UserScreenGraph.UserScreen.route
        ) {
            val viewModel: UserViewModel = hiltViewModel()
            UserRoute(
                viewModel = viewModel,
                onClick = { userUiEvents ->
                    when (userUiEvents) {
                        is UserUiEvents.WorldCupItemClick -> {
                        }
                        is UserUiEvents.WineItemClick -> {
                            appState.navigateRoute(NavigationRoute.UserScreenGraph.UserReviewDetailScreen.route + "/${userUiEvents.wine.id}")
                        }
                        is UserUiEvents.EditButtonClick -> {
                            appState.navigateRoute(NavigationRoute.UserScreenGraph.EditUserProfileBottomSheet.route)
                        }
                        is UserUiEvents.SettingButtonClick -> {
                            appState.navigateRoute(NavigationRoute.UserScreenGraph.SettingScreen.route)
                        }
                    }
                }
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
        ) { navBackStackEntry ->
            val viewModel: CategoryViewModel = hiltViewModel()
            val category = navBackStackEntry.arguments?.getString("category")
            val index = appState.categoryList.withIndex().filter { text -> text.value.title == category }.map { it.index }[0]

            LaunchedEffect(true) {
                if (category != null) {
                    viewModel.updateCategory(category = category)
                }
                viewModel.getWineListWithPageAndCategory()
            }

            CategoryRoute(
                viewModel = viewModel,
                index = index,
                categoryList = appState.categoryList,
                onClick = { categoryUiEvents ->
                    when (categoryUiEvents) {
                        is CategoryUiEvents.WineBoardClick -> {
                            appState.navigateRoute(route = NavigationRoute.ReviewGraph.ReviewDetailScreen.route + "/${categoryUiEvents.wine.id}")
                        }
                        is CategoryUiEvents.BackButtonClick -> {
                            appState.navigateBackStack()
                        }
                        is CategoryUiEvents.TabClick -> {
                            viewModel.updateCategory(category = categoryUiEvents.tag)
                            viewModel.getWineListWithPageAndCategory()
                        }
                    }
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
            route = NavigationRoute.UserScreenGraph.SettingScreen.route
        ) {
            val viewModel: SettingViewModel = hiltViewModel()
            SettingRoute(
                viewModel = viewModel,
                onButtonClick = { settingScreenEvents ->
                    when (settingScreenEvents) {
                        is SettingUiEvents.LeaveButtonClick -> {
                            appState.navigateRoute(NavigationRoute.UserScreenGraph.LeaveScreen.route)
                        }
                        is SettingUiEvents.BackButtonClick -> {
                            appState.navigateBackStack()
                        }
                        is SettingUiEvents.EditButtonClick -> {
                            appState.navigateRoute(NavigationRoute.UserScreenGraph.EditUserProfileBottomSheet.route)
                        }
                    }
                }
            )
        }
        composable(
            route = NavigationRoute.UserScreenGraph.LeaveScreen.route
        ) {
            val viewModel: LeaveViewModel = hiltViewModel()
            LeaveRoute(
                viewModel = viewModel,
                leaveState = { leaveUiState ->
                    when (leaveUiState) {
                        is LeaveUiState.Success -> {
                            appState.showProgressBar()
                            appState.navigateBackStack()
                        }
                        is LeaveUiState.Loading -> {
                            appState.showProgressBar()
                        }
                        is LeaveUiState.Error -> {}
                        is LeaveUiState.Init -> {}
                    }
                },
                onClick = { leaveUiEvents ->
                    when (leaveUiEvents) {
                        is LeaveUiEvents.KeepUsingButtonClick -> {
                            appState.navigateBackStack()
                        }
                        is LeaveUiEvents.BackButtonClick -> {
                            appState.navigateBackStack()
                        }
                        is LeaveUiEvents.LeaveButtonClick -> {
                            viewModel.leaveMembership(userId = 0L)
                        }
                    }
                }
            )
        }
        composable(
            route = NavigationRoute.UserScreenGraph.UserReviewDetailScreen.route + "/{wineId}"
        ) {
            val viewModel: UserReviewDetailViewModel = hiltViewModel()
            UserReviewDetailRoute(
                viewModel = viewModel,
                onClick = { userReviewDetailUiEvents ->
                    when (userReviewDetailUiEvents) {
                        is UserReviewDetailUiEvents.ShareImageButtonClick -> {
                        }
                        is UserReviewDetailUiEvents.BackButtonClick -> {
                            appState.navigateBackStack()
                        }
                        is UserReviewDetailUiEvents.EditReviewButtonClick -> {
                            Timber.tag("wineId").d(userReviewDetailUiEvents.wineId.toString())
                            appState.navigateRoute(NavigationRoute.ReviewGraph.ReviewDetailScreen.route + "/${userReviewDetailUiEvents.wineId}")
                        }
                    }
                }
            )
        }
        bottomSheet(
            route = NavigationRoute.UserScreenGraph.EditUserProfileBottomSheet.route
        ) { backStackEntry ->
            val parentEntry = remember(backStackEntry) {
                appState.navController.getBackStackEntry(NavigationRoute.UserScreenGraph.UserScreen.route)
            }
            val viewModel: UserViewModel = hiltViewModel(parentEntry) // sharedViewModel

            LaunchedEffect(key1 = true) {
                viewModel.getUserProfileImages()
            }

            EditUserProfileRoute(
                viewModel = viewModel,
                onSubmitState = { updateProfileUiEventState ->
                    when (updateProfileUiEventState) {
                        is UpdateProfileUiEventState.Success -> {
                            viewModel.getUserData()
                            appState.navController.navigate(NavigationRoute.UserScreenGraph.UserScreen.route) {
                                popUpTo(findStartDestination(graph = appState.navController.graph).id)
                                launchSingleTop = true
                            }
                        }
                        is UpdateProfileUiEventState.Loading -> {
                        }
                        is UpdateProfileUiEventState.Error -> {}
                        is UpdateProfileUiEventState.Init -> {}
                    }
                }
            )
        }
    }
}

internal fun NavGraphBuilder.reviewGraph(
    appState: ZuzuAppState
) {
    navigation(
        route = NavigationRoute.ReviewGraph.route,
        startDestination = "${NavigationRoute.ReviewGraph.ReviewDetailScreen.route}/{wineId}"
    ) {
        composable(
            route = "${NavigationRoute.ReviewGraph.ReviewDetailScreen.route}/{wineId}",
            arguments = listOf(
                navArgument("wineId") {
                    type = NavType.LongType
                }
            )
        ) {
            ReviewDetailRoute(
                viewModel = hiltViewModel(),
                navigateBack = { appState.navigateBackStack() },
                navigateToReviewWrite = { appState.navigateRoute(NavigationRoute.ReviewGraph.ReviewWriteScreen.route) }
            )
        }

        composable(
            route = NavigationRoute.ReviewGraph.ReviewWriteScreen.route
        ) {
            ReviewWriteRoute(
                viewModel = hiltViewModel(),
                navigateReviewShareCard = { appState.navigateRoute(NavigationRoute.ReviewGraph.ReviewShareCardScreen.route) },
                navigateBack = { appState.navigateBackStack() }
            )
        }

        composable(
            route = NavigationRoute.ReviewGraph.ReviewShareCardScreen.route
        ) {
            ReviewShareCardRoute(
                viewModel = hiltViewModel()
            )
        }
    }
}
