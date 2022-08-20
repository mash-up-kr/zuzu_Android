package com.mashup.zuzu.ui.review

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.mashup.zuzu.compose.component.ReviewCard
import com.mashup.zuzu.compose.theme.ProofTheme
import com.mashup.zuzu.data.model.ReviewShareCard

@Composable
fun ReviewShareCardRoute(
    viewModel: ReviewShareCardViewModel
) {
    val reviewShareCard by viewModel.reviewShareCardState.collectAsState()

    ReviewShareCardScreen(reviewShareCard)
}

@Composable
fun ReviewShareCardScreen(
    reviewShareCard: ReviewShareCard
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(top = 54.dp)
    ) {
        Text(
            text = "리뷰 완성",
            style = ProofTheme.typography.headingXL,
            color = ProofTheme.color.white,
            textAlign = TextAlign.Center
        )

        Text(
            text = "proof 테이스팅 노트를 자랑해보세요.",
            style = ProofTheme.typography.bodyL,
            color = ProofTheme.color.gray200,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 8.dp)
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.8f)
        ) {
            ReviewCard(
                reviewShareCard = reviewShareCard,
                modifier = Modifier.padding(top = 24.dp, bottom = 16.dp, start = 24.dp, end = 24.dp)
            )
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
                    text = "이미지로 공유",
                    style = ProofTheme.typography.buttonL,
                    textAlign = TextAlign.Center,
                    color = ProofTheme.color.white
                )
            }
        }
    }
}

// TODO: 서버에서 내려주는 Category 상수가 확정되는대로 분기를 추가해줄 것
fun getDrinkCategoryColor(category: String): Long {
    val color = when (category) {
        "Beer" -> 0xFFFFD9BE
        else -> 0xFFDFF8A2
    }

    return color
}
