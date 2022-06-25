package com.mashup.zuzu.ui.worldcup

import androidx.annotation.RawRes
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.airbnb.lottie.compose.*
import com.mashup.zuzu.R
import com.mashup.zuzu.ui.navigation.WorldCupScreen
import com.mashup.zuzu.ui.theme.ZuzuAndroidTheme
import com.mashup.zuzu.ui.theme.darkgray

/**
 * @Created by 김현국 2022/06/23
 * @Time 1:52 오후
 */

@Composable
fun WorldCupStartPage(
    navController: NavHostController
) {

    val composition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(R.raw.colortrophy)
    )
    val lottieAnimatable = rememberLottieAnimatable()

    LaunchedEffect(composition) {
        lottieAnimatable.animate(
            composition = composition,
            clipSpec = LottieClipSpec.Frame(0, 1200),
            initialProgress = 0.1f
        )
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {

        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = "술드컵을 \n 시작해보세요!",
            style = TextStyle(
                fontSize = 40.sp,
                fontWeight = Bold
            ),
            textAlign = TextAlign.Center
        )
        LottieAnimation(
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .fillMaxHeight(0.8f)
                .align(Alignment.CenterHorizontally),
            composition = composition,
            progress = { lottieAnimatable.progress },
            contentScale = ContentScale.FillHeight
        )
        Button(
            onClick = {
                navController.navigate(WorldCupScreen.WorldCupSelectPersonScreen.route)
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp)
                .align(Alignment.CenterHorizontally)
                .padding(10.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = darkgray,
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(
                text = "시작하기!"
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewWorldCupStartPage() {
    ZuzuAndroidTheme() {
        WorldCupStartPage(rememberNavController())
//
    }
}
