package com.mashup.zuzu.ui.review

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.mashup.zuzu.compose.component.BlurImage
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
                .width(296.dp)
                .height(460.dp)
                .padding(top = 24.dp, bottom = 16.dp)
        ) {
            ReviewCard(reviewShareCard = reviewShareCard)
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

@Composable
fun ReviewCard(reviewShareCard: ReviewShareCard) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(560.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(396.dp)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            ProofTheme.color.gray500,
                            ProofTheme.color.gray500
                        )
                    ),
                    alpha = 0.6f,
                    shape = RoundedCornerShape(12.dp)
                )
                .align(Alignment.BottomCenter)
        )

        Box(
            modifier = Modifier.align(Alignment.TopCenter)
        ) {
            Card(
                modifier = Modifier
                    .width(270.dp)
                    .height(330.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                BlurImage {
                    AsyncImage(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(),
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(reviewShareCard.wine.imageUrl).build(),
                        contentDescription = null,
                        contentScale = ContentScale.FillHeight
                    )
                }
            }
        }

        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(end = 12.dp)
            ,
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 12.dp),
                horizontalAlignment = Alignment.End
            ) {
                val categoryColor = getDrinkCategoryColor(reviewShareCard.wine.category)

                Text(
                    text = "NO.002",
                    style = ProofTheme.typography.headingXS,
                    color = ProofTheme.color.gray500,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(bottom = 14.dp)
                        .background(
                            color = Color(categoryColor),
                            shape = RoundedCornerShape(4.dp)
                        )
                        .padding(6.dp)
                )

                Text(
                    text = reviewShareCard.userReview.createdAt.split("T")[0],
                    style = ProofTheme.typography.headingXS,
                    color = Color(categoryColor),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(bottom = 16.dp)
                        .background(
                            color = ProofTheme.color.gray500,
                            shape = RoundedCornerShape(4.dp)
                        )
                        .padding(6.dp)
                )
            }

            Column(
                modifier = Modifier
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                ProofTheme.color.gray500,
                                ProofTheme.color.gray500
                            )
                        ),
                        alpha = 0.6f,
                        shape = RoundedCornerShape(bottomStart = 12.dp, bottomEnd = 12.dp)
                    ),
//                    .align(Alignment.BottomCenter),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                /**
                 * Drink Description
                 */
                Text(
                    text = reviewShareCard.wine.name,
                    color = ProofTheme.color.white,
                    style = ProofTheme.typography.headingS,
                    maxLines = 2,
                    modifier = Modifier.padding(start = 8.dp, end = 8.dp, top = 12.dp)
                )

                Spacer(
                    modifier = Modifier
                        .padding(vertical = 12.dp)
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(color = ProofTheme.color.black)
                )

                /**
                 * Drink Info
                 */
                Text(
                    text = reviewShareCard.wine.category,
                    color = ProofTheme.color.white,
                    style = ProofTheme.typography.headingXS,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )

                Text(
                    text = "ALC ${reviewShareCard.wine.alc}% . FROM reviewShareCard.wine.origin",
                    color = ProofTheme.color.white,
                    style = ProofTheme.typography.buttonM,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )

                Spacer(
                    modifier = Modifier
                        .padding(vertical = 12.dp)
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(color = ProofTheme.color.black)
                )

                /**
                 * Review Info
                 */
                Text(
                    text = "${reviewShareCard.userReview.mood} ${reviewShareCard.userReview.weather},${reviewShareCard.userReview.time}" +
                            "\n${reviewShareCard.userReview.isHeavy}|${reviewShareCard.userReview.isBitter}|${reviewShareCard.userReview.isBurning}|${reviewShareCard.userReview.isStrong}",
                    color = ProofTheme.color.white,
                    style = ProofTheme.typography.bodyS,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(bottom = 12.dp)
                )
            }
        }
    }
}

//TODO: 서버에서 내려주는 Category 상수가 확정되는대로 분기를 추가해줄 것
private fun getDrinkCategoryColor(category: String): Long {
    val color = when (category) {
        "Beer" -> 0xFFFFD9BE
        else -> 0xFFDFF8A2
    }

    return color
}
