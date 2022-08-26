package com.mashup.zuzu.ui.review

import android.app.Activity
import android.content.Intent
import androidx.activity.compose.BackHandler
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.mashup.zuzu.R
import com.mashup.zuzu.compose.component.WineImageCardForReviewDetail
import com.mashup.zuzu.compose.theme.ProofTheme
import com.mashup.zuzu.data.model.Wine
import com.mashup.zuzu.data.response.model.Result
import com.mashup.zuzu.ui.login.LoginActivity
import com.mashup.zuzu.util.Key
import timber.log.Timber

@Composable
fun ReviewDetailRoute(
    viewModel: ReviewDetailViewModel,
    navigateBack: () -> Unit,
    navigateToReviewWrite: (Long) -> Unit
) {
    val evaluationUiState by viewModel.evaluationUiState.collectAsState()
    val wineDataUiState by viewModel.wineDataUiState.collectAsState()
    val wineId = viewModel.wineId

    when (wineDataUiState) {
        is WineDataUiState.Loading -> {
        }
        is WineDataUiState.Success -> {
            ReviewDetailScreen(
                wineData = (wineDataUiState as WineDataUiState.Success).wineData,
                evaluationUiState = evaluationUiState,
                navigateBack = navigateBack,
                navigateToReviewWrite = { navigateToReviewWrite(wineId) },
                checkAccount = viewModel::checkAccount
            )
        }
    }
}

@Composable
fun ReviewDetailScreen(
    wineData: Wine,
    evaluationUiState: EvaluationUiState,
    navigateBack: () -> Unit,
    navigateToReviewWrite: () -> Unit,
    checkAccount: () -> Boolean
) {
    val scrollState = rememberScrollState()

    val context = LocalContext.current

    val launcher =
        rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == Activity.RESULT_OK) {
                navigateToReviewWrite()
            } else {
                Timber.tag("ReviewResultScreen").e("User not login")
            }
        }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .verticalScroll(scrollState)
    ) {
        WineImageCardForReviewDetail(wine = wineData)

        WineInformation(content = wineData.description ?: "")

        WorldCupInfo(
            dummyWorldCupData = DummyWorldCupData(
                first = wineData.worldcupWinCount ?: 0,
                fourth = wineData.worldcupSemiFinalCount ?: 0
            )
        )

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

        when (evaluationUiState) {
            is EvaluationUiState.Success -> {
                WineByReview(result = evaluationUiState.result)
            }
            is EvaluationUiState.NoItem -> {
                EmptyReviewScreen(modifier = Modifier.padding(horizontal = 24.dp))
            }
            is EvaluationUiState.Loading -> {
            }
        }

        Button(
            onClick = {
                if (checkAccount()) {
                    navigateToReviewWrite()
                } else {
                    val intent = Intent(context, LoginActivity::class.java)
                    intent.putExtra(Key.REQUEST_LOGIN_FROM_OTHER_CASES, true)
                    launcher.launch(intent)
                }
            },
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
        }
        Spacer(modifier = Modifier.height(16.dp))

        Column(
            modifier = Modifier
                .background(
                    color = ProofTheme.color.gray600,
                    shape = RoundedCornerShape(8.dp)
                )
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxSize()
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "\uD83E\uDD47 1위 선정",
                        style = ProofTheme.typography.headingS,
                        color = ProofTheme.color.white
                    )

                    Text(
                        text = dummyWorldCupData.first.toString() + " 번",
                        style = ProofTheme.typography.headingS,
                        color = ProofTheme.color.white
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "\uD83E\uDD49 4강 진출",
                        style = ProofTheme.typography.headingS,
                        color = ProofTheme.color.white
                    )
                    Text(
                        text = dummyWorldCupData.fourth.toString() + " 번",
                        style = ProofTheme.typography.headingS,
                        color = ProofTheme.color.white
                    )
                }
            }
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
    result: Result,
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

        LazyHorizontalGrid(
            rows = GridCells.Adaptive(30.dp),
            modifier = Modifier
                .padding(top = 12.dp)
                .fillMaxWidth()
                .height(80.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(result.situation) {
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
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        ) {
            GradientHorizontalProgress(
                left = result.isBitter.Sweet,
                right = result.isBitter.Bitter,
                leftKeyword = "달아요",
                rightKeyword = "써요",
                modifier = Modifier
                    .padding(bottom = 12.dp)
                    .clip(RoundedCornerShape(8.dp))
            )

            GradientHorizontalProgress(
                left = result.isHeavy.Light,
                right = result.isHeavy.Heavy,
                leftKeyword = "가벼워요",
                rightKeyword = "무거워요",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp)
                    .clip(RoundedCornerShape(8.dp))
            )

            GradientHorizontalProgress(
                left = result.isStrong.Mild,
                right = result.isStrong.Strong,
                leftKeyword = "은은한 술맛",
                rightKeyword = "찐한 술맛",
                modifier = Modifier
                    .padding(bottom = 12.dp)
                    .clip(RoundedCornerShape(8.dp))
            )

            GradientHorizontalProgress(
                left = result.isBurning.Smooth,
                right = result.isBurning.Burning,
                leftKeyword = "부드러운 목넘김",
                rightKeyword = "화끈거리는 목넘김",
                modifier = Modifier
                    .padding(bottom = 12.dp)
                    .clip(RoundedCornerShape(8.dp))
            )
        }

        Text(
            text = "한마디로 말하면,",
            style = ProofTheme.typography.headingS,
            color = ProofTheme.color.white,
            modifier = Modifier.padding(top = 40.dp, bottom = 22.dp)
        )

        SummaryPieChart(
            size = LocalConfiguration.current.screenWidthDp.dp / 3,
            tasteInfo = result.taste.map { Pair(it.tasteName, it.percent) },
            percents = result.taste.map { it.percent }
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
            result.pairing.forEach {
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

        val shape = RoundedCornerShape(8.dp)

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
                        shape = RoundedCornerShape(topStart = 8.dp, bottomStart = 8.dp)
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
                        shape = RoundedCornerShape(topStart = 8.dp, bottomStart = 8.dp)
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
                        shape = RoundedCornerShape(topStart = 8.dp, bottomStart = 8.dp)
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

            Column(
                horizontalAlignment = Alignment.End
            ) {
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
    percents: List<Int>,
    colors: List<Color> = listOf(
        Color(0xFF4F17C5),
        Color(0xFF6748E3),
        Color(0xFF9685FF),
        Color(0xFF2A2C3C)
    ),
    tasteInfo: List<Pair<String, Int>>,
    size: Dp
) {
    val sweepAngles = percents.map {
        360 * (it / 100)
    }

    val gray = ProofTheme.color.gray600
    val background = ProofTheme.color.black

    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Canvas(
            modifier = Modifier.size(size)
        ) {
            var preAngle = 0f

            for (i in sweepAngles.indices) {
                drawArc(
                    color = colors[i],
                    startAngle = preAngle,
                    sweepAngle = preAngle + sweepAngles[i],
                    useCenter = true
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
            tasteInfo.forEach { it ->
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
                        text = it.first,
                        style = ProofTheme.typography.bodyXS600,
                        color = ProofTheme.color.white
                    )

                    Text(
                        text = "${it.second}%",
                        style = ProofTheme.typography.bodyXS,
                        color = ProofTheme.color.white
                    )
                }
            }
        }
    }
}
