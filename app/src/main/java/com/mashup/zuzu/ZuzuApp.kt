package com.mashup.zuzu

import android.app.Activity
import android.content.Intent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.navigation.material.ModalBottomSheetLayout
import com.mashup.zuzu.compose.theme.ProofTheme
import com.mashup.zuzu.ui.home.ZuzuBottomNavigationBar
import com.mashup.zuzu.ui.login.LoginActivity
import com.mashup.zuzu.ui.navigation.*
import com.mashup.zuzu.util.Key
import timber.log.Timber

/**
 * @Created by 김현국 2022/06/30
 */
@OptIn(ExperimentalMaterialNavigationApi::class)
// TODO: Naming 변경 필요함
@Composable
fun ZuzuApp(
    onWorldCupButtonClick: () -> Unit
) {
    val zuzuAppState = rememberAppState()
    val viewModel: ProofAppViewModel = hiltViewModel()
    val context = LocalContext.current

    val launcher =
        rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == Activity.RESULT_OK) {
                zuzuAppState.navigateToBottomBarRoute(
                    "user_screen.screen"
                )
            } else {
                Timber.tag("UserScreen").e("User not login")
            }
        }

    ModalBottomSheetLayout(
        bottomSheetNavigator = zuzuAppState.bottomSheetNavigator,
        sheetBackgroundColor = ProofTheme.color.gray600,
        sheetShape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
    ) {
        Scaffold(
            floatingActionButtonPosition = FabPosition.End,
            isFloatingActionButtonDocked = false,
            floatingActionButton = {
                if (zuzuAppState.shouldShowFloatingButton) {
                    FloatingActionButton(
                        modifier = Modifier
                            .width(64.dp)
                            .height(64.dp),
                        shape = CircleShape,
                        onClick = {
                            onWorldCupButtonClick()
                        },
                        backgroundColor = ProofTheme.color.primary300
                    ) {
                        Image(
                            modifier = Modifier
                                .width(40.dp)
                                .height(40.dp),
                            painter = painterResource(id = R.drawable.ic_worldcup),
                            contentDescription = null
                        )
                    }
                }
            },
            backgroundColor = ProofTheme.color.black,
            bottomBar = {
                if (zuzuAppState.shouldShowBottomBar) { // bottomBarTabs의 BottomScreen의 경로에 있을 때만, BottomNavBar가 보이도록 했습니다.
                    ZuzuBottomNavigationBar(
                        navController = zuzuAppState.navController,
                        onBottomTabsClick = { route ->
                            if (viewModel.checkAccount()) {
                                zuzuAppState.navigateToBottomBarRoute(route)
                            } else {
                                val intent = Intent(context, LoginActivity::class.java)
                                intent.putExtra(Key.REQUEST_LOGIN_FROM_OTHER_CASES, true)
                                launcher.launch(intent)
                            }
                        },
                        bottomNavigationItems = zuzuAppState.bottomBarRoutes
                    )
                }
            }
        ) { paddingValues ->
            Box {
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
                // progressbar
                if (zuzuAppState.shouldShowProgressBar) {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
        }
    }
}
