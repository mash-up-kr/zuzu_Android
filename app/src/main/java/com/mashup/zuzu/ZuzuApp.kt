package com.mashup.zuzu.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.zuzu_android.R
import com.mashup.zuzu.rememberAppState
import com.mashup.zuzu.ui.theme.ZuzuAndroidTheme

/**
 * @Created by 김현국 2022/06/30
 * @Time 4:41 오후
 */
@Composable
fun ZuzuApp() {
    val zuzuAppState = rememberAppState()
    Scaffold(
        floatingActionButtonPosition = FabPosition.Center,
        isFloatingActionButtonDocked = true,
        floatingActionButton = {
            FloatingActionButton(
                modifier = Modifier.width(48.dp).height(48.dp),
                shape = CircleShape,
                onClick = {
                    zuzuAppState.navigateToBottomBarRoute(BottomScreen.WorldCup.route)
                },
                backgroundColor = Color.White
            ) {
                Image(
                    modifier = Modifier
                        .width(24.dp)
                        .height(24.dp),
                    painter = painterResource(id = R.drawable.ic_worldcup),
                    contentDescription = null
                )
            }
        },
        bottomBar = {
            if (zuzuAppState.shouldShowBottomBar) // bottomBarTabs의 BottomScreen의 경로에 있을 때만, BottomNavBar가 보이도록 했습니다.
                ZuzuBottomNavigationBar(
                    zuzuAppState.currentRoute,
                    onBottomTabsClick = { route ->
                        zuzuAppState.navigateToBottomBarRoute(route)
                    },
                    bottomNavigationItems = zuzuAppState.bottomBarTabs
                )
        }
    ) { paddingValues ->
        NavHost(
            modifier = Modifier.padding(paddingValues),
            navController = zuzuAppState.navController,
            startDestination = BottomScreen.Navigation.route
        ) {
            composable(BottomScreen.Navigation.route) {
                // ZuzuHomeScreen()
            }
            composable(BottomScreen.User.route) {
            }
            composable(BottomScreen.WorldCup.route) {
            }
        }
    }
}

@Preview
@Composable
fun PreviewZuzuApp() {
    ZuzuAndroidTheme {
        ZuzuApp()
    }
}
