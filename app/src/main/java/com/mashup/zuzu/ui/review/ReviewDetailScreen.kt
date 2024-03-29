package com.mashup.zuzu.ui.review

import android.app.Activity
import android.content.Intent
import androidx.activity.compose.BackHandler
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Scaffold
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
import com.mashup.zuzu.compose.component.ReviewTopBar
import com.mashup.zuzu.compose.component.WineImageCardForReviewDetail
import com.mashup.zuzu.compose.theme.ProofTheme
import com.mashup.zuzu.data.mapper.mapperKoreanToPainter
import com.mashup.zuzu.data.model.Wine
import com.mashup.zuzu.data.response.model.Result
import com.mashup.zuzu.ui.login.LoginActivity
import com.mashup.zuzu.util.Key
import timber.log.Timber

@Composable
fun ReviewDetailRoute(
    viewModel: ReviewDetailViewModel,
    navigateBack: () -> Unit,
    navigateToReviewWrite: (Long) -> Unit,
    onClickTopBar: (ReviewDetailUiEvents) -> Unit
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
                checkAccount = viewModel::checkAccount,
                onClickTopBar = onClickTopBar
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ReviewDetailScreen(
    wineData: Wine,
    evaluationUiState: EvaluationUiState,
    navigateBack: () -> Unit,
    navigateToReviewWrite: () -> Unit,
    checkAccount: () -> Boolean,
    onClickTopBar: (ReviewDetailUiEvents) -> Unit
) {
    val scrollState = rememberLazyListState()

    val context = LocalContext.current

    val launcher =
        rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == Activity.RESULT_OK) {
                navigateToReviewWrite()
            } else {
                Timber.tag("ReviewResultScreen").e("User not login")
            }
        }

    Scaffold(
        modifier = Modifier.fillMaxWidth().fillMaxHeight(),
        bottomBar = {
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
    ) {
        CompositionLocalProvider(
            LocalOverscrollConfiguration provides null
        ) {
            Box {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .background(color = ProofTheme.color.black)
                        .padding(it),
                    state = scrollState
                ) {
                    item {
                        WineImageCardForReviewDetail(
                            wine = wineData,
                            modifier = Modifier.fillMaxWidth().aspectRatio(1f)
                        )
                    }

                    item {
                        WineInformation(content = wineData.description ?: "")
                    }

                    item {
                        WorldCupInfo(
                            dummyWorldCupData = DummyWorldCupData(
                                first = wineData.worldcupWinCount ?: 0,
                                fourth = wineData.worldcupSemiFinalCount ?: 0
                            )
                        )
                    }

                    item {
                        Spacer(
                            modifier = Modifier
                                .padding(vertical = 40.dp)
                                .fillMaxWidth()
                                .height(6.dp)
                                .background(color = ProofTheme.color.gray600)
                        )
                    }

                    item {
                        Text(
                            text = "마셔본 사람들은 이 술을",
                            style = ProofTheme.typography.headingL,
                            color = ProofTheme.color.white,
                            modifier = Modifier.padding(horizontal = 24.dp)
                        )
                    }

                    item {
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
                    }
                    item {
                        Spacer(
                            modifier = Modifier.height(52.dp)
                        )
                    }
                }
                ReviewTopBar(
                    scrollState = scrollState,
                    onClick = onClickTopBar
                )
            }

            BackHandler {
                navigateBack()
            }
        }
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
    Column {
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
                    Row(
                        modifier = Modifier
                            .background(
                                color = ProofTheme.color.gray500,
                                shape = RoundedCornerShape(8.dp)
                            )
                            .padding(7.dp),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            modifier = Modifier.align(Alignment.CenterVertically),
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
                tasteInfo = result.taste.take(3).map { Pair(it.tasteName, it.percent) },
                percents = result.taste.take(3).map { it.percent }
            )
        }
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 40.dp, bottom = 40.dp)
                .height(6.dp)
                .background(color = ProofTheme.color.gray600)
        )

        Text(
            modifier = Modifier.padding(start = 24.dp, end = 24.dp),
            text = "많이 곁들인 음식은",
            style = ProofTheme.typography.headingS,
            color = ProofTheme.color.white
        )

        Row(
            modifier = Modifier
                .padding(top = 20.dp, start = 24.dp, end = 24.dp)
        ) {
            result.pairing.forEach {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(end = 20.dp)
                ) {
                    Image(
                        painter = mapperKoreanToPainter(pairing = it),
                        contentDescription = "",
                        modifier = Modifier
                            .width(52.dp)
                            .height(52.dp)
                    )
                    Spacer(modifier = Modifier.height(4.dp))

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
        Color(0xFF9685FF)
    ),
    tasteInfo: List<Pair<String, Int>>,
    size: Dp
) {
    val sweepAngles = percents.map {
        360 * (it.toFloat() / 100)
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

            drawCircle(
                color = gray,
                radius = (size / 2).toPx()
            )

            for (i in sweepAngles.indices) {
                drawArc(
                    color = colors[i],
                    startAngle = preAngle,
                    sweepAngle = (preAngle + sweepAngles[i]) - preAngle,
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
            tasteInfo.forEachIndexed { index, taste ->
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
                    Canvas(
                        modifier = Modifier.size(8.dp)
                    ) {
                        drawCircle(
                            color = colors[index],
                            radius = (4.dp).toPx()
                        )
                    }

                    Text(
                        text = taste.first,
                        style = ProofTheme.typography.bodyXS600,
                        color = ProofTheme.color.white
                    )

                    Text(
                        text = "${taste.second}%",
                        style = ProofTheme.typography.bodyXS,
                        color = ProofTheme.color.white
                    )
                }
            }
        }
    }
}
