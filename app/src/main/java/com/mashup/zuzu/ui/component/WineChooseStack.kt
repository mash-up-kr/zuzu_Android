package com.mashup.zuzu.ui.component

import androidx.compose.animation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mashup.zuzu.data.model.Wine
import com.mashup.zuzu.ui.worldcup.WorldCupViewModel
import com.mashup.zuzu.ui.theme.ZuzuAndroidTheme

/**
 * @Created by 김현국 2022/06/21
 * @Time 2:20 오후
 */

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun WineChooseStack(
    onWineClick: (Wine, Int) -> Unit,
    viewModel: WorldCupViewModel,
    clickState: MutableList<Boolean>
) {

    val showWinePair = viewModel.showWinePair.collectAsState()

    Row(
        horizontalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        if (showWinePair.value.size != 1) {
            showWinePair.value.forEachIndexed { index, wine ->
                WineCard(
                    wine,
                    index,
                    onWineClick = onWineClick,
                    onCardClick = {
                        clickState[index] = !clickState[index] // 클릭시 state 변화
                    },
                    clickState = clickState[index], // state에 따른 animation변화
                )
            }
        } else {
            // 우승자 ~
            Column() {
                WineCard(wine = showWinePair.value.first(), index = 0, onWineClick = { wine, i -> null }, onCardClick = {}, clickState = false)
                Text("월드컵 끝~!")
            }
        }
    }
}

@Preview
@Composable
fun PreviewWineChooseStack() {
    ZuzuAndroidTheme() {
//            WineChooseStack(onWineClick = {})
    }
}
