package com.mashup.zuzu.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.zuzu_android.R
import com.mashup.zuzu.ui.theme.LightGray
import com.mashup.zuzu.ui.theme.ZuzuAndroidTheme

/**
 * @Created by 김현국 2022/06/30
 * @Time 5:08 오후
 */

sealed class BottomScreen(val route: String) {
    object Navigation : BottomScreen("Navigation")
    object User : BottomScreen("User")
    object WorldCup : BottomScreen("WorldCup")
}

val bottomNavigationItems = listOf(
    BottomScreen.Navigation,
    BottomScreen.User
)

@Composable
fun ZuzuBottomNavigationBar(
    currentRoute: String?,
    onBottomTabsClick: (String) -> Unit,
    bottomNavigationItems: List<BottomScreen>
) {
    BottomNavigation(
        modifier = Modifier
            .clip(
                RoundedCornerShape(24.dp, 24.dp, 0.dp, 0.dp)
            )
            .height(52.dp),
        backgroundColor = LightGray
    ) {
        bottomNavigationItems.forEach { screen ->
            BottomNavigationItem(
                icon = {
                    when (screen.route) {
                        "Navigation" -> {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_compass),
                                contentDescription = null
                            )
                        }
                        "User" -> {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_user),
                                contentDescription = null
                            )
                        }
                    }
                },
                selected = currentRoute == screen.route, // 선택에 따라서 색상이 변경됩니다.
                alwaysShowLabel = false,
                onClick = {
                    onBottomTabsClick(screen.route)
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewZuzuHomeScreen() {
    ZuzuAndroidTheme {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            // ZuzuHomeScreen()
        }
    }
}

@Composable
fun PreviewZuzuNavigationBar() {
    ZuzuAndroidTheme() {
        ZuzuBottomNavigationBar(
            currentRoute = null,
            onBottomTabsClick = {},
            bottomNavigationItems = bottomNavigationItems
        )
    }
}
