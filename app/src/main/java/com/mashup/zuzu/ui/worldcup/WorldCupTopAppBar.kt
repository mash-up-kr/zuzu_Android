package com.mashup.zuzu.ui.worldcup

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.mashup.zuzu.ui.component.BackButton
import com.mashup.zuzu.ui.component.CloseButton
import com.mashup.zuzu.ui.theme.ZuzuAndroidTheme

/**
 * @Created by 김현국 2022/06/23
 * @Time 6:46 오후
 */
@Composable
fun WorldCupTopAppBar(
    modifier: Modifier,
    onWorldCupTopBarItemClick: (String) -> Unit
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        BackButton(modifier = Modifier, onBackButtonClick = onWorldCupTopBarItemClick)

        CloseButton(modifier = Modifier, onCloseButtonClick = onWorldCupTopBarItemClick)
    }
}

@Preview
@Composable
fun PreviewWorldCupTopAppBar() {
    ZuzuAndroidTheme() {
        WorldCupTopAppBar(modifier = Modifier.fillMaxWidth(), {})
    }
}
