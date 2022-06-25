package com.mashup.zuzu.ui.worldcup

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.mashup.zuzu.ui.component.RoundCard
import com.mashup.zuzu.ui.theme.ZuzuAndroidTheme

/**
 * @Created by 김현국 2022/06/23
 * @Time 4:05 오후
 */

data class Round(
    val subDescription: String,
    val mainRound: Int,
)

val roundItem = listOf(
    Round(
        "심플하게", 8
    ),
    Round(
        "심플하게", 16
    ),
    Round(
        "심플하게", 32
    ),
    Round(
        "심플하게", 64
    )
)
@Composable
fun WorldCupSelectRoundPage(
    navController: NavHostController,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {

        Column(
            modifier = Modifier.align(Alignment.Start).padding(start = 30.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "라운드를 \n선택해주세요!",
                style = TextStyle(
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Bold
                ),
            )
            Text(
                "원하시는 선택지를 골라주세요",
                style = TextStyle(
                    fontWeight = FontWeight.Light,
                    color = Color.LightGray
                ),
            )
        }

        LazyColumn(
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            items(roundItem) { round ->
                RoundCard(
                    subDescription = round.subDescription, mainRound = round.mainRound, modifier = Modifier.fillMaxWidth().height(100.dp),
                    onRoundClick = { navController.navigate("WorldCupSelectItemScreen/${round.mainRound}") }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewWorldCupSelectRoundPage() {
    ZuzuAndroidTheme() {
        WorldCupSelectRoundPage(rememberNavController())
//
    }
}
