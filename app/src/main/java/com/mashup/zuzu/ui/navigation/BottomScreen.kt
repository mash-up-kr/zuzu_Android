package com.mashup.zuzu.ui.navigation

/**
 * @Created by 김현국 2022/06/25
 * @Time 5:25 오후
 */

sealed class BottomScreen(val route: String, val title: String) {
    object Navigation : BottomScreen("Navigation", "Navigation")
    object User : BottomScreen("User", "User")
}
val bottomNavigationItems = listOf(
    BottomScreen.Navigation,
    BottomScreen.User

)
