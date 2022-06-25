package com.mashup.zuzu.ui.worldcup

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.mashup.zuzu.ui.component.WineChooseStack
import com.mashup.zuzu.ui.theme.ZuzuAndroidTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * @Created by 김현국 2022/06/21
 * @Time 3:45 오후
 */

@Composable
fun WorldCup(
    navController: NavHostController,
    mainRound: Int?,
    viewModel: WorldCupViewModel = viewModel(factory = WorldCupViewModel.provideFactory()),
    coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.Main)
) { // 뷰모델에 main Round 전송
    val topCount = viewModel.currentSelectedCount.collectAsState()
    val clickState = remember { mutableStateListOf(false, false) }
    Column(
        horizontalAlignment = CenterHorizontally
    ) {
        Text(
            "${topCount.value}",
            modifier = Modifier.fillMaxWidth(),
            style = TextStyle(
                fontSize = 50.sp,
                fontWeight = FontWeight.Bold,
            ),
            textAlign = TextAlign.Center
        )
        WineChooseStack(
            onWineClick = { wine, index ->
                coroutineScope.launch {
                    delay(1000)
                    clickState[index] = false // 재처리
                    viewModel.addWine(wine)
                }
            }, viewModel = viewModel,
            clickState = clickState
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewWorldCup() {
    ZuzuAndroidTheme() {
        WorldCup(rememberNavController(), 16)
    }
}
