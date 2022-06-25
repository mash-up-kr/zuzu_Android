package com.mashup.zuzu.ui.navigation

/**
 * @Created by 김현국 2022/06/21
 * @Time 3:41 오후
 */

sealed class WorldCupScreen(val route: String, val title: String) {
    object WorldCupStartScreen : WorldCupScreen("WorldCupStart", "WorldCupStart")
    object WorldCupSelectPersonScreen : WorldCupScreen("WorldCupSelectPersonScreen", "WorldCupSelectPersonScreen")
    object WorldCupSelectRoundScreen : WorldCupScreen("WorldCupSelectRoundScreen", "WorldCupSelectRoundScreen")
    object WorldCupSelectStateScreen : WorldCupScreen("WorldCupSelectStateScreen", "WorldCupSelectStateScreen")
    object WorldCupSelectItemScreen : WorldCupScreen("WorldCupSelectItemScreen", "WorldCupSelectItemScreen")
}

val worldCupScreenList = listOf(
    WorldCupScreen.WorldCupStartScreen.route,
    WorldCupScreen.WorldCupSelectPersonScreen.route,
    WorldCupScreen.WorldCupSelectRoundScreen.route,
    WorldCupScreen.WorldCupSelectStateScreen.route,
    WorldCupScreen.WorldCupSelectItemScreen.route,
)
