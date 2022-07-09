package com.mashup.zuzu

import androidx.compose.runtime.*
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.mashup.zuzu.ui.home.bottomNavigationItems

/**
 * @Created by 김현국 2022/06/30
 * @Time 5:01 오후
 */

@Stable
class ZuzuAppState(
    val navController: NavHostController
) {

    val bottomBarTabs = bottomNavigationItems
    private val bottomBarRoutes = bottomBarTabs.map { it.route }

    val shouldShowBottomBar: Boolean @Composable get() = navController.currentBackStackEntryAsState().value?.destination?.route in bottomBarRoutes

    val currentRoute: String?
        get() = navController.currentDestination?.route

    fun navigateToBottomBarRoute(route: String) {
        if (route != currentRoute) {
            navController.navigate(route) {
                launchSingleTop = true
                restoreState = true
                popUpTo(findStartDestination(graph = navController.graph).id) {
                    saveState = true
                }
            }
        }
    }

    fun navigateBackStack() {
        navController.popBackStack()
    }
}

private val NavGraph.startDestination: NavDestination?
    get() = findNode(startDestinationId)

private tailrec fun findStartDestination(graph: NavDestination): NavDestination {
    return if (graph is NavGraph) findStartDestination(graph = graph.startDestination!!) else graph
}

@Composable
fun rememberAppState(
    navController: NavHostController = rememberNavController()
) = remember(navController) {
    ZuzuAppState(navController = navController)
}
