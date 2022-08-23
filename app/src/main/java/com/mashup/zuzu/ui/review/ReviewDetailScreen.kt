package com.mashup.zuzu.ui.review

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.mashup.zuzu.R
import com.mashup.zuzu.compose.component.WineImageCardForReviewDetail
import com.mashup.zuzu.compose.theme.ProofTheme
import com.mashup.zuzu.data.model.ReviewDetailResponse
import com.mashup.zuzu.data.model.Taste

@Composable
fun ReviewDetailRoute(
    viewModel: ReviewDetailViewModel,
    navigateBack: () -> Unit,
    navigateToReviewWrite: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    when (uiState) {
        is ReviewDetailUiState.Loading -> {
            //TODO : 로딩 화면 처리
        }

        is ReviewDetailUiState.Normal -> {
            ReviewDetailScreen(
                reviewDetailUiState = uiState as ReviewDetailUiState.Normal,
                navigateBack = navigateBack,
                navigateToReviewWrite = navigateToReviewWrite
            )
        }
    }
}

@Composable
fun ReviewDetailScreen(
    reviewDetailUiState: ReviewDetailUiState.Normal,
    navigateBack: () -> Unit,
    navigateToReviewWrite: () -> Unit
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .verticalScroll(scrollState)
    ) {
        WineImageCardForReviewDetail(wine = reviewDetailUiState.wine)

        WineInformation(content = reviewDetailUiState.wine.description ?: "")

        WorldCupInfo(reviewDetailUiState.dummyWorldCupData)

        Spacer(
            modifier = Modifier
                .padding(vertical = 40.dp)
                .fillMaxWidth()
                .height(6.dp)
                .background(color = ProofTheme.color.gray600)
        )

        Text(
            text = "마셔본 사람들은 이 술을",
            style = ProofTheme.typography.headingL,
            color = ProofTheme.color.white,
            modifier = Modifier.padding(horizontal = 24.dp)
        )

        if (reviewDetailUiState.reviewDetailResponse == null) {
            EmptyReviewScreen(modifier = Modifier.padding(horizontal = 24.dp))
        } else {
            WineByReview(reviewDetailUiState.reviewDetailResponse)
        }

        Button(
            onClick = navigateToReviewWrite,
            colors = ButtonDefaults.buttonColors(backgroundColor = ProofTheme.color.primary300),
            modifier = Modifier
                .padding(top = 50.dp)
                .fillMaxWidth()
                .height(52.dp)
        ) {
            Text(
                text = "나도 리뷰하기",
                style = ProofTheme.typography.buttonL,
                color = ProofTheme.color.white
            )
        }
    }

    BackHandler {
        navigateBack()
    }
}

@Composable
fun WineInformation(
    content: String
) {
    Column(
        modifier = Modifier.padding(top = 40.dp, start = 24.dp, end = 24.dp)
    ) {
        Text(
            text = "정보",
            style = ProofTheme.typography.headingL,
            color = ProofTheme.color.white
        )
        Text(
            text = "품종, 역사 등을 포함한 정보예요.",
            style = ProofTheme.typography.bodyXS,
            color = ProofTheme.color.gray300,
            modifier = Modifier.padding(top = 4.dp)
        )
        Text(
            text = content,
            style = ProofTheme.typography.bodyM,
            color = ProofTheme.color.white,
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}

@Composable
fun WorldCupInfo(
    dummyWorldCupData: DummyWorldCupData
) {
    Column(
        modifier = Modifier.padding(top = 40.dp, start = 24.dp, end = 24.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "월드컵에서는",
                style = ProofTheme.typography.headingM,
                color = ProofTheme.color.white
            )

            Image(
                painter = painterResource(id = R.drawable.ic_result_help), contentDescription = ""
            )
        }

        Box(
            modifier = Modifier
                .padding(top = 20.dp)
                .background(
                    color = ProofTheme.color.gray600,
                    shape = RoundedCornerShape(8.dp)
                )
                .fillMaxWidth()
        ) {
            Text(
                text = dummyWorldCupData.first.toString(),
                style = ProofTheme.typography.headingS,
                color = ProofTheme.color.white,
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}

@Composable
fun EmptyReviewScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .padding(20.dp)
            .fillMaxWidth()
            .height(238.dp)
            .background(
                color = ProofTheme.color.gray600,
                shape = RoundedCornerShape(8.dp)
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(painter = painterResource(id = R.drawable.ic_review_empty), contentDescription = "")

        Text(
            text = "아직 리뷰가 없어요 :(",
            style = ProofTheme.typography.headingM,
            color = ProofTheme.color.white
        )

        Text(
            text = "이 술의 첫번째 리뷰어가 되어보세요!",
            style = ProofTheme.typography.bodyM,
            color = ProofTheme.color.gray200
        )
    }
}

@Composable
fun WineByReview(
    reviewDetailResponse: ReviewDetailResponse,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier.padding(start = 24.dp, end = 24.dp)
    ) {
        Text(
            text = "이럴 때 마셨어요",
            style = ProofTheme.typography.headingS,
            color = ProofTheme.color.white,
            modifier = Modifier.padding(top = 20.dp)
        )

        Text(
            text = "많이 답한 순으로 보여드려요.",
            style = ProofTheme.typography.bodyXS,
            color = ProofTheme.color.gray200,
            modifier = Modifier.padding(top = 4.dp)
        )

        LazyVerticalGrid(
            columns = GridCells.Adaptive(50.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .padding(top = 12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(reviewDetailResponse.situation) {
                Text(
                    text = it,
                    style = ProofTheme.typography.bodyS600,
                    color = ProofTheme.color.white,
                    modifier = Modifier
                        .background(
                            color = ProofTheme.color.gray500,
                            shape = RoundedCornerShape(8.dp)
                        )
                        .padding(10.dp)
                )
            }
        }

        Text(
            text = "이렇게 표현했어요",
            style = ProofTheme.typography.headingS,
            color = ProofTheme.color.white,
            modifier = Modifier.padding(top = 40.dp)
        )

        Column(
            modifier = Modifier.padding(top = 16.dp),
        ) {
            GradientHorizontalProgress(
                left = reviewDetailResponse.isBitter.sweet,
                right = reviewDetailResponse.isBitter.bitter,
                leftKeyword = "달아요",
                rightKeyword = "써요",
                modifier = Modifier.padding(bottom = 12.dp)
            )

            GradientHorizontalProgress(
                left = reviewDetailResponse.isHeavy.light,
                right = reviewDetailResponse.isHeavy.heavy,
                leftKeyword = "달아요",
                rightKeyword = "써요",
                modifier = Modifier.padding(bottom = 12.dp)
            )

            GradientHorizontalProgress(
                left = reviewDetailResponse.isStrong.mild,
                right = reviewDetailResponse.isStrong.strong,
                leftKeyword = "달아요",
                rightKeyword = "써요",
                modifier = Modifier.padding(bottom = 12.dp)
            )

            GradientHorizontalProgress(
                left = reviewDetailResponse.isBurning.smooth,
                right = reviewDetailResponse.isBurning.burning,
                leftKeyword = "달아요",
                rightKeyword = "써요",
                modifier = Modifier.padding(bottom = 12.dp)
            )
        }

        Text(
            text = "한마디로 말하면,",
            style = ProofTheme.typography.headingS,
            color = ProofTheme.color.white,
            modifier = Modifier.padding(top = 40.dp)
        )

        SummaryPieChart(
            size = LocalConfiguration.current.screenWidthDp.dp / 3,
            tastes = reviewDetailResponse.taste
        )

        Spacer(
            modifier = Modifier
                .padding(vertical = 40.dp)
                .fillMaxWidth()
                .height(6.dp)
                .background(color = ProofTheme.color.gray600)
        )

        Text(
            text = "많이 곁들인 음식은",
            style = ProofTheme.typography.headingS,
            color = ProofTheme.color.white
        )

        Row(
            modifier = Modifier
                .padding(top = 20.dp)
        ) {
            reviewDetailResponse.pairing.forEach {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(end = 20.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_menu_category),
                        contentDescription = "",
                        modifier = Modifier
                            .width(52.dp)
                            .height(52.dp)
                    )

                    Text(
                        text = it,
                        style = ProofTheme.typography.bodyS,
                        color = ProofTheme.color.white
                    )
                }
            }
        }
    }
}

@Composable
fun GradientHorizontalProgress(
    left: Int,
    right: Int,
    leftKeyword: String,
    rightKeyword: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
    ) {
        val leftPercent = (left.toFloat() / (left + right))
        val rightPercent = (right.toFloat() / (left + right))
        val percent: Float

        val shape = RoundedCornerShape(topStart = 8.dp, bottomStart = 8.dp)

        val greenBrush = Brush.horizontalGradient(
            listOf(
                ProofTheme.color.gray500,
                ProofTheme.color.green300
            )
        )

        val purpleBrush = Brush.horizontalGradient(
            listOf(
                ProofTheme.color.primary300,
                ProofTheme.color.gray500
            )
        )

        if (leftPercent > rightPercent) {
            percent = leftPercent

            Box(
                modifier = Modifier
                    .background(
                        color = ProofTheme.color.gray500,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .fillMaxWidth()
                    .height(50.dp)
            ) {}

            Box(
                modifier = Modifier
                    .background(
                        brush = greenBrush,
                        shape = shape
                    )
                    .fillMaxWidth(percent)
                    .height(50.dp)
            ) {}

        } else if (leftPercent < rightPercent) {
            percent = leftPercent

            Box(
                modifier = Modifier
                    .background(
                        brush = purpleBrush,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .fillMaxWidth()
                    .height(50.dp)
            ) {}

            Box(
                modifier = Modifier
                    .background(
                        color = ProofTheme.color.gray500,
                        shape = shape
                    )
                    .fillMaxWidth(percent)
                    .height(50.dp)
            ) {}
        } else {
            percent = leftPercent

            Box(
                modifier = Modifier
                    .background(
                        brush = purpleBrush,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .fillMaxWidth()
                    .height(50.dp)
            ) {}

            Box(
                modifier = Modifier
                    .background(
                        brush = greenBrush,
                        shape = shape
                    )
                    .fillMaxWidth(percent)
                    .height(50.dp)
            ) {}

        }

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            Column() {
                Text(
                    text = leftKeyword,
                    style = ProofTheme.typography.bodyS600,
                    color = ProofTheme.color.white
                )

                Text(
                    text = left.toString(),
                    style = ProofTheme.typography.body3XS,
                    color = ProofTheme.color.white
                )
            }

            Column() {
                Text(
                    text = rightKeyword,
                    style = ProofTheme.typography.bodyS600,
                    color = ProofTheme.color.white
                )

                Text(
                    text = right.toString(),
                    style = ProofTheme.typography.body3XS,
                    color = ProofTheme.color.white
                )
            }
        }
    }
}

@Composable
fun SummaryPieChart(
    values: List<Float> = listOf(15f, 35f, 15f, 15f),
    colors: List<Color> = listOf(
        Color(0xFF4F17C5),
        Color(0xFF6748E3),
        Color(0xFF9685FF),
        Color(0xFF2A2C3C)
    ),
    tastes: List<Taste>,
    size: Dp
) {
    // Convert each proportions to angle
    val sweepAngles = values.map {
        360 * (it / 100)
    }

    val gray = ProofTheme.color.gray600
    val background = ProofTheme.color.black

    Row() {
        Canvas(
            modifier = Modifier.size(size)
        ) {
            var preAngle = 0f

            for (i in sweepAngles.indices) {
                drawArc(
                    color = colors[i],
                    startAngle = preAngle,
                    sweepAngle = preAngle + sweepAngles[i],
                    useCenter = true,
                )

                preAngle += sweepAngles[i]
            }

            drawCircle(
                color = background,
                radius = (size / 5).toPx()
            )

        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp)
        ) {
            tastes.forEach { it ->
                Row(
                    modifier = Modifier
                        .padding(bottom = 10.dp)
                        .fillMaxWidth()
                        .background(
                            color = ProofTheme.color.gray600,
                            shape = RoundedCornerShape(4.dp)
                        )
                        .padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    Text(
                        text = it.tasteName,
                        style = ProofTheme.typography.bodyXS600,
                        color = ProofTheme.color.white
                    )

                    Text(
                        text = it.percent,
                        style = ProofTheme.typography.bodyXS,
                        color = ProofTheme.color.white
                    )
                }
            }
        }
    }

}
