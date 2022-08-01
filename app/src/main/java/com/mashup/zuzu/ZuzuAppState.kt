package com.mashup.zuzu

import androidx.compose.runtime.*
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.navigation.material.BottomSheetNavigator
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.navigation.material.rememberBottomSheetNavigator
import com.mashup.zuzu.data.model.Category
import com.mashup.zuzu.ui.navigation.shouldShowFloatingButtonInScreen
import com.mashup.zuzu.ui.navigation.shouldShowNavigationItems

/**
 * @Created by 김현국 2022/06/30
 */

@Stable
@OptIn(ExperimentalMaterialNavigationApi::class)
class ZuzuAppState constructor(
    bottomSheetNavigator: BottomSheetNavigator,
    val navController: NavHostController,
) {
    var categoryList = mutableStateListOf<Category>()
    var shouldShowProgressBar: Boolean by mutableStateOf(false)

    var bottomSheetNavigator by mutableStateOf(bottomSheetNavigator)

    val bottomBarRoutes = shouldShowNavigationItems.map { it.route }
    val shouldShowBottomBar: Boolean @Composable
    get() = navController.currentBackStackEntryAsState().value?.destination?.route in bottomBarRoutes

    private val floatingButtonRoutes = shouldShowFloatingButtonInScreen.map { it.route }
    val shouldShowFloatingButton: Boolean @Composable
    get() = navController.currentBackStackEntryAsState().value?.destination?.route in floatingButtonRoutes

    private val currentRoute: String?
        get() = navController.currentDestination?.route

    val currentDestination @Composable get() = navController.currentBackStackEntryAsState().value?.destination

    fun navigateToBottomBarRoute(route: String) {
        if (route != currentRoute) {
            navController.navigate(route) {
                popUpTo(findStartDestination(graph = navController.graph).id) {
                    saveState = true
                }
                launchSingleTop = true
                restoreState = true
            }
        }
    }

    fun navigateRoute(route: String) {
        navController.navigate(route)
    }

    fun navigateBackStack() {
        navController.popBackStack()
    }

    fun navigateDetailScreen() {
        navController.navigate(REVIEW_DETAIL)
    }

    fun navigateReviewWriteScreen() {
        navController.navigate(REVIEW_WRITE)
    }

    fun showProgressBar() {
        shouldShowProgressBar = !shouldShowProgressBar
    }

    fun putCategoryList(category: List<Category>) {
        categoryList = category.toMutableStateList()
    }
    companion object {
        const val REVIEW_DETAIL = "reviewDetail"
        const val REVIEW_WRITE = "reviewWrite"
    }
}

private val NavGraph.startDestination: NavDestination?
    get() = findNode(startDestinationId)

private tailrec fun findStartDestination(graph: NavDestination): NavDestination {
    return if (graph is NavGraph) findStartDestination(graph = graph.startDestination!!) else graph
}

@OptIn(ExperimentalMaterialNavigationApi::class)
@Composable
fun rememberAppState(
    bottomSheetNavigator: BottomSheetNavigator = rememberBottomSheetNavigator(),
    navController: NavHostController = rememberNavController(bottomSheetNavigator),
) = remember(navController) {
    ZuzuAppState(
        bottomSheetNavigator = bottomSheetNavigator,
        navController = navController,
    )
}
