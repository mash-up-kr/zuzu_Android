package com.mashup.zuzu

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
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
import com.mashup.zuzu.R
import com.mashup.zuzu.ZuzuAppState.Companion.BOTTOM_SCREEN_USER
import com.mashup.zuzu.ZuzuAppState.Companion.BOTTOM_SCREEN_WORLD_CUP
import com.mashup.zuzu.ZuzuAppState.Companion.REVIEW_DETAIL
import com.mashup.zuzu.ZuzuAppState.Companion.REVIEW_WRITE
import com.mashup.zuzu.ui.DetailScreen
import com.mashup.zuzu.ui.category.CategoryScreen
import com.mashup.zuzu.ui.home.ZuzuBottomNavigationBar
import com.mashup.zuzu.ui.home.ZuzuHomeScreen
import com.mashup.zuzu.ui.theme.Black
import com.mashup.zuzu.ui.review.ReviewWriteRoute
import com.mashup.zuzu.ui.theme.ProofTheme

/**
 * @Created by 김현국 2022/06/30
 * @Time 4:41 오후
 */
//TODO: Naming 변경 필요함
@Composable
fun ZuzuApp() {
    val zuzuAppState = rememberAppState()

    Scaffold(
        floatingActionButtonPosition = FabPosition.End,
        isFloatingActionButtonDocked = false,
        floatingActionButton = {
            if (zuzuAppState.shouldShowBottomBar)
                FloatingActionButton(
                    modifier = Modifier.width(64.dp).height(64.dp),
                    shape = CircleShape,
                    onClick = {
                        zuzuAppState.navigateToBottomBarRoute(BOTTOM_SCREEN_WORLD_CUP)
                    },
                    backgroundColor = Color.White
                ) {
                    Image(
                        modifier = Modifier
                            .width(40.dp)
                            .height(40.dp),
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
                    bottomNavigationItems = zuzuAppState.bottomBarRoutes
                )
        }
    ) { paddingValues ->
        NavHost(
            modifier = Modifier.padding(paddingValues),
            navController = zuzuAppState.navController,
            startDestination = REVIEW_DETAIL
        ) {
            composable(ZuzuAppState.BOTTOM_SCREEN_NAVIGATION) {
                ZuzuHomeScreen(
                    modifier = Modifier.background(color = Black),
                    onCategoryClick = { category ->
                        zuzuAppState.navigateToBottomBarRoute(ZuzuAppState.BOTTOM_SCREEN_CATEGORY + "/${category.title}")
                    },
                    onWineBoardClick = {},
                    onWorldCupItemClick = {}
                )
            }

            composable(BOTTOM_SCREEN_USER) {
            }

            composable(BOTTOM_SCREEN_WORLD_CUP) {
            }

            composable(REVIEW_DETAIL) {
                DetailScreen(
                    navigateToReviewWrite = { zuzuAppState.navigateReviewWriteScreen() }
                )
            }

            composable(REVIEW_WRITE) {
                ReviewWriteRoute()
            }

            composable(ZuzuAppState.BOTTOM_SCREEN_CATEGORY + "/{category}") {
                CategoryScreen(
                    modifier = Modifier.fillMaxHeight().fillMaxWidth().background(color = Black),
                    category = it.arguments!!.getString("category")!!, onBackButtonClick = {
                        zuzuAppState.navigateBackStack()
                    }, onWineBoardClick = {}
                )
            }
        }
    }
}


@Preview
@Composable
fun PreviewZuzuApp() {
    ProofTheme() {
        ZuzuApp()
    }
}
