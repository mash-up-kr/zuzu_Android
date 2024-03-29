package com.mashup.zuzu.ui.navigation

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.mashup.zuzu.R

/**
 * @Created by 김현국 2022/07/23
 */
sealed class NavigationRoute(val route: String) {
    object HomeScreenGraph : NavigationRoute("home_screen") {
        object HomeScreen : NavigationRoute("home_screen.screen")
    }

    object CategoryScreenGraph : NavigationRoute("category_screen") {
        object CategoryScreen : NavigationRoute("category_screen.screen")
    }

    object UserScreenGraph : NavigationRoute("user_screen") {
        object UserScreen : NavigationRoute("user_screen.screen")
        object SettingScreen : NavigationRoute("user_screen.setting")
        object EditUserProfileBottomSheet : NavigationRoute("user_screen.edit_user_profile")
        object LeaveScreen : NavigationRoute("user_screen.leave")
        object UserReviewDetailScreen : NavigationRoute("user_screen.review")
    }

    object ReviewGraph : NavigationRoute("review_screen") {
        object ReviewDetailScreen : NavigationRoute("review_screen.detail")
        object ReviewWriteScreen : NavigationRoute("review_screen.write")
        object ReviewShareCardScreen : NavigationRoute("review_screen.share")
    }
}

sealed class BottomNavigationItemsWithIcon(
    val route: String,
    val icon: Int,
    val xOffset: Dp
) {
    object Home : BottomNavigationItemsWithIcon(NavigationRoute.HomeScreenGraph.HomeScreen.route, R.drawable.ic_home, 20.dp)
    object User : BottomNavigationItemsWithIcon(NavigationRoute.UserScreenGraph.UserScreen.route, R.drawable.ic_user, (-20).dp)
}
val shouldShowNavigationItems = listOf(
    BottomNavigationItemsWithIcon.Home,
    BottomNavigationItemsWithIcon.User
)
val shouldShowFloatingButtonInScreen = listOf(
    NavigationRoute.HomeScreenGraph.HomeScreen
)
