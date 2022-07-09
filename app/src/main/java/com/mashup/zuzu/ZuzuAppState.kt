package com.mashup.zuzu

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
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

    val bottomBarRoutes = bottomNavigationItems

    val shouldShowBottomBar: Boolean
        @Composable
        get() = navController.currentBackStackEntryAsState().value?.destination?.route in bottomBarRoutes

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

    fun navigateDetailScreen() {
        navController.navigate(REVIEW_DETAIL)
    }

    fun navigateReviewWriteScreen() {
        navController.navigate(REVIEW_WRITE)
    }

    companion object {
        const val BOTTOM_SCREEN_NAVIGATION = "navigation"
        const val BOTTOM_SCREEN_USER = "user"
        const val BOTTOM_SCREEN_WORLD_CUP = "worldCup"

        const val REVIEW_DETAIL = "reviewDetail"
        const val REVIEW_WRITE = "reviewWrite"
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
