package com.mashup.zuzu

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.navigation.material.ModalBottomSheetLayout
import com.mashup.zuzu.ui.home.ZuzuBottomNavigationBar
import com.mashup.zuzu.ui.navigation.*
import com.mashup.zuzu.ui.theme.ProofTheme

/**
 * @Created by 김현국 2022/06/30
 * @Time 4:41 오후
 */
@OptIn(ExperimentalMaterialNavigationApi::class)
// TODO: Naming 변경 필요함
@Composable
fun ZuzuApp(
    onWorldCupButtonClick: () -> Unit
) {
    val zuzuAppState = rememberAppState()

    ModalBottomSheetLayout(
        bottomSheetNavigator = zuzuAppState.bottomSheetNavigator,
        sheetBackgroundColor = ProofTheme.color.gray600,
        sheetShape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
    ) {
        Scaffold(
            floatingActionButtonPosition = FabPosition.End,
            isFloatingActionButtonDocked = false,
            floatingActionButton = {
                if (zuzuAppState.shouldShowFloatingButton)
                    FloatingActionButton(
                        modifier = Modifier
                            .width(64.dp)
                            .height(64.dp),
                        shape = CircleShape,
                        onClick = {
                            onWorldCupButtonClick()
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
            backgroundColor = ProofTheme.color.black,
            bottomBar = {
                if (zuzuAppState.shouldShowBottomBar) // bottomBarTabs의 BottomScreen의 경로에 있을 때만, BottomNavBar가 보이도록 했습니다.
                    ZuzuBottomNavigationBar(
                        currentRoute = zuzuAppState.currentDestination,
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
                startDestination = NavigationRoute.HomeScreenGraph.route // REVIEW_DETAIL
            ) {
                homeGraph(
                    appState = zuzuAppState
                )
                categoryGraph(
                    appState = zuzuAppState
                )
                userGraph(
                    appState = zuzuAppState
                )
                reviewGraph(
                    appState = zuzuAppState
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewZuzuApp() {
    ProofTheme() {
        ZuzuApp({})
    }
}
