package com.mashup.zuzu.ui.navigation

import androidx.annotation.StringRes

/**
 * @Created by 김현국 2022/06/25
 * @Time 5:25 오후
 */

sealed class BottomScreen(val route: String) {
    object Navigation : BottomScreen("Navigation")
    object User : BottomScreen("User")
}
val bottomNavigationItems = listOf(
    BottomScreen.Navigation,
    BottomScreen.User
)
