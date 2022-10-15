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
    val navController: NavHostController
) {
    var categoryList = mutableStateListOf<Category>()
    var shouldShowProgressBar: Boolean by mutableStateOf(false)

    var bottomSheetNavigator by mutableStateOf(bottomSheetNavigator)

    val bottomBarRoutes = shouldShowNavigationItems
    val shouldShowBottomBar: Boolean @Composable
    get() = navController.currentBackStackEntryAsState().value?.destination?.route in bottomBarRoutes.map { it.route }

    private val floatingButtonRoutes = shouldShowFloatingButtonInScreen.map { it.route }
    val shouldShowFloatingButton: Boolean @Composable
    get() = navController.currentBackStackEntryAsState().value?.destination?.route in floatingButtonRoutes

    private val currentRoute: String?
        get() = navController.currentDestination?.route

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

    fun showProgressBar() {
        shouldShowProgressBar = !shouldShowProgressBar
    }

    fun putCategoryList(category: List<Category>) {
        categoryList = category.toMutableStateList()
    }
}

private val NavGraph.startDestination: NavDestination?
    get() = findNode(startDestinationId)

tailrec fun findStartDestination(graph: NavDestination): NavDestination {
    return if (graph is NavGraph) findStartDestination(graph = graph.startDestination!!) else graph
}

@OptIn(ExperimentalMaterialNavigationApi::class)
@Composable
fun rememberAppState(
    bottomSheetNavigator: BottomSheetNavigator = rememberBottomSheetNavigator(),
    navController: NavHostController = rememberNavController(bottomSheetNavigator)
) = remember(bottomSheetNavigator, navController) {
    ZuzuAppState(
        bottomSheetNavigator = bottomSheetNavigator,
        navController = navController
    )
}
