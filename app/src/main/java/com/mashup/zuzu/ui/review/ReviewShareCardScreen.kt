package com.mashup.zuzu.ui.review

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.mashup.zuzu.R
import com.mashup.zuzu.compose.theme.ProofTheme

@Composable
fun ReviewShareCardRoute(
    viewModel: ReviewShareCardViewModel
) {
    ReviewShareCardScreen()
}

@Composable
fun ReviewShareCardScreen() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Text(
            text = "리뷰 등장.",
            style = ProofTheme.typography.headingXL,
            color = ProofTheme.color.white,
            textAlign = TextAlign.Center
        )

        Text(
            text = "proof에서 간편하게 만든 테이스팅 노트를\n자랑해보세요.",
            style = ProofTheme.typography.bodyL,
            color = ProofTheme.color.gray200,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 8.dp)
        )

        Box(
            modifier = Modifier
                .width(296.dp)
                .height(460.dp)
                .padding(top = 24.dp, bottom = 16.dp)
        ) {

        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, start = 24.dp, end = 24.dp)
        ) {
            Button(
                onClick = { /*TODO*/ },
                colors = ButtonDefaults.buttonColors(backgroundColor = ProofTheme.color.gray400),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .weight(1f)
                    .height(52.dp)
            ) {
                Text(
                    text = "확인",
                    style = ProofTheme.typography.buttonL,
                    textAlign = TextAlign.Center,
                    color = ProofTheme.color.white
                )
            }

            Button(
                onClick = { /*TODO*/ },
                colors = ButtonDefaults.buttonColors(backgroundColor = ProofTheme.color.primary300),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .weight(2f)
                    .height(52.dp)
                    .padding(start = 8.dp)
            ) {
                Text(
                    text = "공유하기",
                    style = ProofTheme.typography.buttonL,
                    textAlign = TextAlign.Center,
                    color = ProofTheme.color.white
                )
            }

        }
    }
}
