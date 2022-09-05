package com.mashup.zuzu.ui.navigation

import android.content.Intent
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat.startActivity
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
import com.mashup.zuzu.ui.worldcup.WorldcupActivity
import com.mashup.zuzu.util.Key
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
            val context = LocalContext.current
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
                            val worldCupId = homeUiEvents.bestWorldCup.winnerDrinkId

                            val intent = Intent(context, WorldcupActivity::class.java)
                            intent.putExtra(Key.RECOMMENDED_WORLDCUP_ID, worldCupId.toString())
                            context.startActivity(intent)
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
            val context = LocalContext.current
            UserRoute(
                viewModel = viewModel,
                onClick = { userUiEvents ->
                    when (userUiEvents) {
                        is UserUiEvents.WorldCupItemClick -> {
                            val intent: Intent = Intent(context, WorldcupActivity::class.java)
                            val winnerDrinkId = userUiEvents.bestWorldCup.winnerDrinkId
                            intent.putExtra(Key.MY_WINNING_DRINK_ID, winnerDrinkId)
                            context.startActivity(intent)
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
            val index =
                appState.categoryList.withIndex().filter { text -> text.value.title == category }
                    .map { it.index }[0]

            val networkState by viewModel.connection.collectAsState()

            LaunchedEffect(networkState) {
                if (category != null && networkState) {
                    viewModel.updateCategory(category = category)
                    if (viewModel.page.value != 1) {
                        viewModel.getWineListWithPageAndCategoryNextPage()
                    } else {
                        viewModel.getWineListWithPageAndCategory()
                    }
                }
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
                        is SettingUiEvents.LogoutButtonClick -> {
                            viewModel.logout()
                        }
                        is SettingUiEvents.MoveToHome -> {
                            appState.navController.navigate(NavigationRoute.HomeScreenGraph.HomeScreen.route) {
                                popUpTo(findStartDestination(graph = appState.navController.graph).id) {
                                    inclusive = true
                                }
                                launchSingleTop = true
                            }
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
        ) { navBackStackEntry ->
            val viewModel: UserReviewDetailViewModel = hiltViewModel()
            val wineId = navBackStackEntry.arguments?.getString("wineId")?.toLong() ?: 0L
            val networkState by viewModel.connection.collectAsState()
            val context = LocalContext.current
            LaunchedEffect(key1 = networkState) {
                if (networkState) {
                    viewModel.updateDrinkId(wineId = wineId)
                    viewModel.getWineReviewListWithPage(context = context)
                }
            }

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
                navigateToReviewWrite = {
                    appState.navigateRoute(NavigationRoute.ReviewGraph.ReviewWriteScreen.route + "/$it")
                }
            )
        }

        composable(
            route = "${NavigationRoute.ReviewGraph.ReviewWriteScreen.route}/{wineId}",
            arguments = listOf(
                navArgument("wineId") {
                    type = NavType.LongType
                }
            )
        ) {
            ReviewWriteRoute(
                viewModel = hiltViewModel(),
                navigateReviewShareCard = { appState.navigateRoute(NavigationRoute.ReviewGraph.ReviewShareCardScreen.route + "/$it") },
                navigateBack = { appState.navigateBackStack() }
            )
        }

        composable(
            route = "${NavigationRoute.ReviewGraph.ReviewShareCardScreen.route}/{reviewId}",
            arguments = listOf(
                navArgument("reviewId") {
                    type = NavType.LongType
                }
            )
        ) {
            ReviewShareCardRoute(
                viewModel = hiltViewModel(),
                onButtonClick = {
                    Timber.tag("appState").d(appState.navController.toString())
                }
            )
        }
    }
}
