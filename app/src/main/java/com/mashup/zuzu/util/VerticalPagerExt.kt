package com.mashup.zuzu.util

import androidx.compose.runtime.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.ui.graphics.Color
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import com.mashup.zuzu.compose.theme.Black
import com.mashup.zuzu.compose.theme.HorizontalPurple

/**
 * @Created by 김현국 2022/09/05
 */

@OptIn(ExperimentalPagerApi::class)
@Composable
fun rememberColor(state: PagerState, page: Int): Color {
    val colorState by remember {
        derivedStateOf {
            if (state.currentPage == page) {
                HorizontalPurple
            } else {
                Black
            }
        }
    }
    return colorState
}
