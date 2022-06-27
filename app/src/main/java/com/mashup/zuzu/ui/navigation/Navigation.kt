package com.mashup.zuzu.ui.navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mashup.zuzu.R
import com.mashup.zuzu.ui.theme.ZuzuAndroidTheme
import com.mashup.zuzu.ui.theme.primary
import com.mashup.zuzu.ui.theme.white
import com.mashup.zuzu.ui.worldcup.WorldCupSelectRoundPage
import com.mashup.zuzu.ui.worldcup.WorldCupStartPage
import com.mashup.zuzu.ui.worldcup.WorldCupTopAppBar

/**
 * @Created by 김현국 2022/06/25
 * @Time 5:05 오후
 */

@Composable
fun Navigation() {
    val appState = rememberAppState()

    Scaffold(
        topBar = {
            if (appState.shouldShowBottomBar) {
                AppTopNavigation(appState.navController)
            } else {
                WorldCupTopAppBar(
                    modifier = Modifier.fillMaxWidth(),
                    onWorldCupTopBarItemClick = { type ->
                        when (type) {
                            "Back" -> {
                                appState.navController.popBackStack()
                            }
                            "Close" -> {
                                appState.navController.navigate(
                                    "Navigation",
                                    navOptions = NavOptions.Builder()
                                        .setPopUpTo("Navigation", true).build()
                                )
                            }
                        }
                    }
                )
            }
        },
        floatingActionButtonPosition = FabPosition.Center,
        isFloatingActionButtonDocked = true,
        floatingActionButton = {
            if (appState.shouldShowBottomBar)
                FloatingActionButton(
                    shape = CircleShape,
                    onClick = {
                        appState.navigateToBottomBarRoute(WorldCupScreen.WorldCupStartScreen.route)
                    },
                    backgroundColor = white
                ) {
                    Image(
                        modifier = Modifier
                            .width(25.dp)
                            .height(25.dp),
                        painter = painterResource(id = R.drawable.worldcup),
                        contentDescription = null
                    )
                }
        },
        bottomBar = {
            if (appState.shouldShowBottomBar)
                AppBottomNavigation(onBottomTabsClick = { route ->
                    appState.navigateToBottomBarRoute(route)
                }, appState.bottomBarTabs)
        }
    ) {
        NavHost(
            navController = appState.navController,
            startDestination = BottomScreen.Navigation.route,
            modifier = Modifier.padding(it)

        ) {
            composable(BottomScreen.Navigation.route) { // Composable을 NavGraphBuilder에 추가한다.
                BottomHome()
            }
            composable(BottomScreen.User.route) {
                BottomUser()
            }
            composable(WorldCupScreen.WorldCupStartScreen.route) {
                WorldCupStartPage(onStartClick = {
                    appState.navigateToBottomBarRoute(WorldCupScreen.WorldCupSelectPersonScreen.route)
                })
            }
            composable(WorldCupScreen.WorldCupSelectPersonScreen.route) {
                WorldCupSelectRoundPage(onRoundClick = { round ->
                    appState.navigateToBottomBarRoute("WorldCupSelectItemScreen/${round.mainRound}")
                })
            }
            composable(WorldCupScreen.WorldCupSelectItemScreen.route + "/{mainRound}") {
                // WorldCup(navController = navController, mainRound = it.arguments?.getString("mainRound")?.toInt())
            }
            composable(WorldCupScreen.WorldCupSelectStateScreen.route) {
            }
        }
    }
}

@Composable
fun AppTopNavigation(
    navController: NavHostController
) {
    Column(
        modifier = Modifier.padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "LOGO",
            textAlign = TextAlign.Start,
            modifier = Modifier.fillMaxWidth()
        )

        Row(
            modifier = Modifier.fillMaxWidth(1f),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("둘러보기")
            IconButton(
                onClick = {}
            ) {
                Icon(imageVector = Icons.Filled.Search, contentDescription = null)
            }
        }

        TopNavigationBar(
            categoryClick = { categoryname ->
                navController.navigate("")
            }
        )
    }
}

@Composable
fun CustomBottomNavigation(
    modifier: Modifier = Modifier,
    backgroundColor: Color = MaterialTheme.colors.primarySurface,
    contentColor: Color = contentColorFor(backgroundColor),
    elevation: Dp = BottomNavigationDefaults.Elevation,
    content: @Composable RowScope.() -> Unit
) {
    Surface(
        color = backgroundColor,
        contentColor = contentColor,
        elevation = elevation,
        modifier = modifier,

    ) {
        Row(
            modifier
                .fillMaxWidth()
                .height(56.dp)
                .selectableGroup(),
            horizontalArrangement = Arrangement.SpaceBetween,
            content = content
        )
    }
}

@Composable
fun AppBottomNavigation(
    onBottomTabsClick: (String) -> Unit,
    bottomNavigationItems: List<BottomScreen>
) {
    CustomBottomNavigation(
        modifier = Modifier
            .clip(
                RoundedCornerShape(24.dp, 24.dp, 0.dp, 0.dp)
            )
            .height(104.dp),
        backgroundColor = primary,

    ) {
        bottomNavigationItems.forEach { screen ->
            BottomNavigationItem(
                icon = {
                    when (screen.route) {
                        "Navigation" -> {
                            Icon(
                                painterResource(id = R.drawable.ic_navigation_outlined),
                                contentDescription = null,
                                modifier = Modifier.offset(y = (-18).dp)
                            )
                        }
                        "User" -> {
                            Icon(
                                painterResource(id = R.drawable.ic_user),
                                contentDescription = null,
                                modifier = Modifier.offset(y = (-18).dp)
                            )
                        }
                    }
                },
                selected = false,
                alwaysShowLabel = false,
                onClick = {
                    onBottomTabsClick(screen.route)
                }
            )
        }
    }
}

@Preview
@Composable
fun PreviewBottomBar() {
    ZuzuAndroidTheme() {
        AppBottomNavigation({ }, bottomNavigationItems = bottomNavigationItems)
    }
}
