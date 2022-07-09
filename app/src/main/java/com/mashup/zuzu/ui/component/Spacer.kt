package com.mashup.zuzu.ui.component

import androidx.compose.foundation.background
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

/**
 * @Created by 김현국 2022/07/01
 * @Time 6:19 오후
 */

@Composable
fun ColorSpacer(
    modifier: Modifier,
    color: Color
) {
    Divider(
        modifier = modifier.background(color = color)
    )
}
