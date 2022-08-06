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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.mashup.zuzu.R
import com.mashup.zuzu.ui.component.WineImageCardForReviewDetail
import com.mashup.zuzu.ui.theme.ProofTheme
import timber.log.Timber

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

        //TODO: 왜 description이 리스트지?
        WineInformation(content = reviewDetailUiState.wine.description)

        WorldCupInfo(reviewDetailUiState.dummyWorldCupData)

        Spacer(
            modifier = Modifier
                .padding(vertical = 40.dp)
                .fillMaxWidth()
                .height(6.dp)
                .background(color = ProofTheme.color.gray600)
        )

        WineByReview(reviewDetailUiState.dummyWineReview)

        Button(
            onClick = navigateToReviewWrite,
            colors = ButtonDefaults.buttonColors(backgroundColor = ProofTheme.color.primary300),
            modifier = Modifier
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
fun WineByReview(
    dummyWineReview: DummyWineReview
) {
    Column(
        modifier = Modifier.padding(top = 40.dp, start = 24.dp, end = 24.dp)
    ) {
        Text(
            text = "마셔본 사람들은 이 술을",
            style = ProofTheme.typography.headingL,
            color = ProofTheme.color.white
        )

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
                .height(300.dp)
                .padding(top = 12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(dummyWineReview.emoji) {
                Box(
                    modifier = Modifier
                        .background(
                            color = ProofTheme.color.gray500,
                            shape = RoundedCornerShape(8.dp)
                        )
                        .padding(10.dp)
                ) {
                    Text(
                        text = it,
                        style = ProofTheme.typography.bodyS600,
                        color = ProofTheme.color.white
                    )
                }
            }
        }

        Text(
            text = "이렇게 표현했어요",
            style = ProofTheme.typography.headingS,
            color = ProofTheme.color.white,
            modifier = Modifier.padding(top = 40.dp)
        )

        Column(
            modifier = Modifier.padding(top = 16.dp)
        ) {
            GreenHorizontalProgress(
                left = dummyWineReview.lightOrHeavy.first,
                right = dummyWineReview.lightOrHeavy.second,
                leftKeyword = "달아요",
                rightKeyword = "써요"
            )

            PurpleHorizontalProgress(
                left = dummyWineReview.softOrDeep.first,
                right = dummyWineReview.softOrDeep.second,
                leftKeyword = "달아요",
                rightKeyword = "써요"
            )
        }

        Text(
            text = "한마디로 말하면,",
            style = ProofTheme.typography.headingS,
            color = ProofTheme.color.white,
            modifier = Modifier.padding(top = 40.dp)
        )

        PieChart1()

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
            dummyWineReview.foods.forEach {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
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
fun GreenHorizontalProgress(
    left: Int,
    right: Int,
    leftKeyword: String,
    rightKeyword: String
) {
    val percent = (left.toFloat() / (left + right))

    Box(
        modifier = Modifier
            .background(
                color = ProofTheme.color.gray500,
                shape = RoundedCornerShape(8.dp)
            )
            .fillMaxWidth()
            .height(50.dp)
    ) {
        Box(
            modifier = Modifier
                .background(
                    brush = Brush.horizontalGradient(
                        listOf(
                            ProofTheme.color.gray500,
                            ProofTheme.color.green300
                        )
                    ),
                    shape = RoundedCornerShape(topStart = 8.dp, bottomStart = 8.dp)
                )
                .fillMaxWidth(percent)
                .height(50.dp)
        ) {}

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
fun PurpleHorizontalProgress(
    left: Int,
    right: Int,
    leftKeyword: String,
    rightKeyword: String
) {
    val percent = (left.toFloat() / (left + right))

    Box(
        modifier = Modifier
            .background(
                brush = Brush.horizontalGradient(
                    listOf(
                        ProofTheme.color.primary300,
                        ProofTheme.color.gray500
                    )
                ),
                shape = RoundedCornerShape(8.dp)
            )
            .fillMaxWidth()
            .height(50.dp)
    ) {
        Box(
            modifier = Modifier
                .background(
                    color = ProofTheme.color.gray500,
                    shape = RoundedCornerShape(topEnd = 8.dp, bottomEnd = 8.dp)
                )
                .fillMaxWidth(percent)
                .height(50.dp)
        ) {}

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
fun PieChart1(
    values: List<Float> = listOf(15f, 35f, 15f),
    colors: List<Color> = listOf(
        Color(0xFF4F17C5),
        Color(0xFF6748E3),
        Color(0xFF9685FF),
        Color(0xFF2A2C3C)
    ),
    legend: List<String> = listOf("상큼달달한 과일", "우유의 부드러움", "묵직한 나무"),
    size: Dp = 200.dp
) {
    // Calculate each proportion value
    val proportions = values.map {
        it * 100 / 100
    }

    // Convert each proportions to angle
    val sweepAngles = proportions.map {
        360 * it / 100
    }

    val background = ProofTheme.color.black

    Row() {
        Canvas(
            modifier = Modifier.size(size)
        ) {

            var startAngle = -90f

            drawArc(
                color = colors[3],
                startAngle = startAngle,
                sweepAngle = 360f,
                useCenter = true
            )

            for (i in sweepAngles.indices) {
                drawArc(
                    color = colors[i],
                    startAngle = startAngle,
                    sweepAngle = sweepAngles[i],
                    useCenter = true
                )
                startAngle += sweepAngles[i]
            }

            drawCircle(
                color = background,
                radius = 100f
            )

        }

        Column() {
            legend.forEach { it ->
                Box(
                    modifier = Modifier
                        .padding(bottom = 12.dp)
                        .background(
                            color = ProofTheme.color.gray600,
                            shape = RoundedCornerShape(8.dp)
                        )
                        .padding(bottom = 8.dp)
                ) {
                    Text(
                        text = it,
                        style = ProofTheme.typography.bodyXS600,
                        color = ProofTheme.color.white
                    )
                }
            }
        }
    }

}
