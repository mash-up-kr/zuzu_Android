package com.mashup.zuzu.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.zuzu_android.R
import com.mashup.zuzu.ZuzuAppState.Companion.BOTTOM_SCREEN_NAVIGATION
import com.mashup.zuzu.ZuzuAppState.Companion.BOTTOM_SCREEN_USER
import com.mashup.zuzu.ui.theme.ProofTheme

/**
 * @Created by 김현국 2022/06/30
 * @Time 5:08 오후
 */

val bottomNavigationItems = listOf(
    BOTTOM_SCREEN_NAVIGATION,
    BOTTOM_SCREEN_USER
)

@Composable
fun ZuzuBottomNavigationBar(
    currentRoute: String?,
    onBottomTabsClick: (String) -> Unit,
    bottomNavigationItems: List<String>
) {
    BottomNavigation(
        modifier = Modifier
            .clip(
                RoundedCornerShape(24.dp, 24.dp, 0.dp, 0.dp)
            )
            .height(52.dp),
        backgroundColor = LightGray
    ) {
        bottomNavigationItems.forEach { route ->
            BottomNavigationItem(
                icon = {
                    when (route) {
                        "navigation" -> {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_compass),
                                contentDescription = null
                            )
                        }
                        "user" -> {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_user),
                                contentDescription = null
                            )
                        }
                    }
                },
                selected = currentRoute == route, // 선택에 따라서 색상이 변경됩니다.
                alwaysShowLabel = false,
                onClick = {
                    onBottomTabsClick(route)
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewZuzuHomeScreen() {
    ProofTheme {
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
    ProofTheme() {
        ZuzuBottomNavigationBar(
            currentRoute = null,
            onBottomTabsClick = {},
            bottomNavigationItems = bottomNavigationItems
        )
    }
}
