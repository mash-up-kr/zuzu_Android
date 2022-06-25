package com.mashup.zuzu.ui.navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.mashup.zuzu.ui.worldcup.WorldCup
import com.mashup.zuzu.ui.worldcup.WorldCupSelectRoundPage
import com.mashup.zuzu.ui.worldcup.WorldCupStartPage
import com.mashup.zuzu.ui.worldcup.WorldCupTopAppBar
import com.mashup.zuzu.R
import com.mashup.zuzu.ui.theme.primary
import com.mashup.zuzu.ui.theme.white

/**
 * @Created by 김현국 2022/06/25
 * @Time 5:05 오후
 */

@Composable
fun currentRoute(
    navController: NavHostController
): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}

@Composable
fun Navigation() {
    val navController = rememberNavController()
    var flag = when (currentRoute(navController)) {
        "Navigation" -> {
            true
        }
        else -> {
            false
        }
    }
    Scaffold(
        topBar = {
            if (flag) {
                AppTopNavigation(navController = navController)
            } else {
                WorldCupTopAppBar(modifier = Modifier.fillMaxWidth(), navController = navController)
            }
        },
        floatingActionButtonPosition = FabPosition.Center,
        isFloatingActionButtonDocked = true,
        floatingActionButton = {
            if (flag)
                FloatingActionButton(
                    shape = CircleShape,
                    onClick = { navController.navigate(WorldCupScreen.WorldCupStartScreen.route) },
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
            if (flag)
                AppBottomNavigation(navController, bottomNavigationItems)
        }
    ) {
        NavHost(
            navController = navController,
            startDestination = BottomScreen.Navigation.route,
            modifier = Modifier.padding(it)

        ) {
            composable(BottomScreen.Navigation.route) { // Composable을 NavGraphBuilder에 추가한다.
                BottomHome(navController)
            }
            composable(BottomScreen.User.route) {
                BottomUser()
            }
            composable(WorldCupScreen.WorldCupStartScreen.route) {
                WorldCupStartPage(navController = navController)
            }
            composable(WorldCupScreen.WorldCupSelectPersonScreen.route) {
                WorldCupSelectRoundPage(navController = navController)
            }
            composable(WorldCupScreen.WorldCupSelectItemScreen.route + "/{mainRound}") {
                WorldCup(navController = navController, mainRound = it.arguments?.getString("mainRound")?.toInt())
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
fun AppBottomNavigation(
    navController: NavHostController,
    bottomNavigationItems: List<BottomScreen>
) {
    BottomNavigation(
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
                                contentDescription = null
                            )
                        }
                        "User" -> {
                            Icon(
                                painterResource(id = R.drawable.ic_user),
                                contentDescription = null
                            )
                        }
                    }
                },
                label = {
                    Text(
                        text = screen.route
                    )
                },
                selected = false,
                alwaysShowLabel = false,
                onClick = {
                    when (screen.route) {
                        "Navigation" -> navController.navigate(BottomScreen.Navigation.route)
                        "User" -> navController.navigate(BottomScreen.User.route)
                    }
                }
            )
        }
    }
}
