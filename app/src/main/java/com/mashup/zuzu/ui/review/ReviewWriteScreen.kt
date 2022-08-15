package com.mashup.zuzu.ui.review

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mashup.zuzu.R
import com.mashup.zuzu.compose.component.WineImageCardForReviewWrite
import com.mashup.zuzu.compose.theme.ProofTheme
import kotlinx.coroutines.launch

@Composable
fun ReviewWriteRoute(
    viewModel: ReviewWriteViewModel = hiltViewModel(),
    navigateReviewShareCard: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    ReviewWriteScreen(
        uiState = uiState,
        navigatePreviousWritePage = viewModel::navigatePreviousWritePage,
        navigateDateSelectPage = viewModel::navigateDateSelectPage,
        navigatePartnerPage = viewModel::navigatePartnerPage,
        navigateGroupPage = viewModel::navigateGroupPage,
        navigateSoloPage = viewModel::navigateSoloPage,
        navigateTastePage = viewModel::navigateTastePage,
        navigateSummaryPage = viewModel::navigateSummaryPage,
        navigateSecondarySummaryPage = viewModel::navigateSecondarySummaryPage,
        navigateReviewShareCard = { place, pairing ->
            navigateReviewShareCard()
            viewModel.navigateReviewShareCard(place, pairing)
        }
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ReviewWriteScreen(
    uiState: ReviewWriteUiState,
    navigatePreviousWritePage: () -> Unit,
    navigateDateSelectPage: (String) -> Unit,
    navigatePartnerPage: (String) -> Unit,
    navigateGroupPage: (String) -> Unit,
    navigateSoloPage: (String) -> Unit,
    navigateTastePage: (Pair<String, Int?>) -> Unit,
    navigateSummaryPage: (List<Int>) -> Unit,
    navigateSecondarySummaryPage: (String) -> Unit,
    navigateReviewShareCard: (String, List<String>) -> Unit
) {
    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState()
    val coroutineScope = rememberCoroutineScope()

    BottomSheetScaffold(
        scaffoldState = bottomSheetScaffoldState,
        sheetShape = RoundedCornerShape(24.dp, 24.dp),
        backgroundColor = ProofTheme.color.black,
        sheetBackgroundColor = ProofTheme.color.gray600,
        sheetContent = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp)
                    .height(200.dp)
            ) {
                Text(
                    text = stringResource(R.string.review_cancel_title),
                    style = ProofTheme.typography.headingL,
                    color = ProofTheme.color.white
                )

                Text(
                    text = stringResource(R.string.review_cancel_content),
                    style = ProofTheme.typography.bodyM,
                    color = ProofTheme.color.gray200,
                    modifier = Modifier.padding(top = 12.dp)
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 48.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Button(
                        onClick = { /*TODO*/ },
                        colors = ButtonDefaults.buttonColors(backgroundColor = ProofTheme.color.gray400),
                        modifier = Modifier
                            .weight(1f)
                            .height(52.dp)
                            .padding(end = 6.dp)
                    ) {
                        Text(
                            text = stringResource(R.string.cancel),
                            style = ProofTheme.typography.buttonL,
                            color = ProofTheme.color.white
                        )
                    }
                    Button(
                        onClick = { /*TODO*/ },
                        colors = ButtonDefaults.buttonColors(backgroundColor = ProofTheme.color.primary300),
                        modifier = Modifier
                            .weight(1f)
                            .height(52.dp)
                            .padding(start = 6.dp)
                    ) {
                        Text(
                            text = stringResource(R.string.confirm),
                            style = ProofTheme.typography.buttonL,
                            color = ProofTheme.color.white
                        )
                    }
                }
            }
        }, sheetPeekHeight = 0.dp, topBar = {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Spacer(Modifier.weight(1f))

                    Text(
                        text = stringResource(R.string.review_write),
                        style = ProofTheme.typography.headingXS,
                        color = ProofTheme.color.white,
                        modifier = Modifier.weight(1f)
                    )

                    IconButton(
                        onClick = {
                            coroutineScope.launch {
                                if (bottomSheetScaffoldState.bottomSheetState.isCollapsed) {
                                    bottomSheetScaffoldState.bottomSheetState.expand()
                                } else {
                                    bottomSheetScaffoldState.bottomSheetState.collapse()
                                }
                            }
                        },
                        modifier = Modifier.padding(end = 20.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_close),
                            contentDescription = "close",
                            tint = Color.Unspecified
                        )
                    }
                }

                LinearProgressIndicator(
                    progress = (uiState.page + 1) / 7f,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(4.dp),
                    backgroundColor = ProofTheme.color.gray500,
                    color = ProofTheme.color.primary300
                )
            }

        }
    ) {
        val scrollState = rememberScrollState()

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .verticalScroll(scrollState)
        ) {
            Column(
                modifier = Modifier.padding(
                    start = 24.dp,
                    end = 24.dp,
                    top = 40.dp,
                    bottom = 34.dp
                ),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                WineImageCardForReviewWrite(
                    wineImageUrl = uiState.wineImageUrl,
                    wineName = uiState.wineName,
                    modifier = Modifier
                        .fillMaxWidth(0.3f)
                        .height(190.dp)
                )

                Topic(
                    totalNum = uiState.page,
                    pageNum = uiState.page,
                    onClickBackButton = navigatePreviousWritePage,
                    modifier = Modifier.padding(top = 40.dp, bottom = 34.dp)
                )

                //TODO: progress 때문에 3,4 페이지에 대해서 다른 처리가 필요함 ex. 3-1, 3-2
                when (uiState.page) {
                    0 -> {
                        WeatherSelectOption(
                            navigateDateSelectPage = navigateDateSelectPage,
                            modifier = Modifier.padding(
                                start = 24.dp,
                                end = 24.dp,
                                top = 40.dp,
                                bottom = 34.dp
                            )
                        )
                    }

                    1 -> {
                        DateSelectOption(
                            navigatePartnerPage = navigatePartnerPage,
                            modifier = Modifier.padding(
                                start = 24.dp,
                                end = 24.dp,
                                top = 40.dp,
                                bottom = 34.dp
                            )
                        )
                    }

                    2 -> {
                        PartnerSelectOption(
                            navigateGroupPage = navigateGroupPage,
                            navigateSoloPage = navigateSoloPage,
                            modifier = Modifier.padding(
                                start = 24.dp,
                                end = 24.dp,
                                top = 40.dp,
                                bottom = 34.dp
                            )
                        )
                    }

                    3 -> {
                        GroupSelectOption(
                            navigateTastePage = navigateTastePage,
                            modifier = Modifier.padding(
                                start = 24.dp,
                                end = 24.dp,
                                top = 40.dp,
                                bottom = 34.dp
                            )
                        )
                    }

                    4 -> {
                        SoloSelectOption(
                            navigateTastePage = navigateTastePage,
                            modifier = Modifier.padding(
                                start = 24.dp,
                                end = 24.dp,
                                top = 40.dp,
                                bottom = 34.dp
                            )
                        )
                    }

                    5 -> {
                        TasteSelectOption(navigateSummaryPage = navigateSummaryPage, modifier = Modifier.padding(
                            start = 24.dp,
                            end = 24.dp,
                            top = 40.dp,
                            bottom = 34.dp
                        ))
                    }

                    6 -> {
                        SummarySelectOption(
                            navigateSecondarySummaryPage = navigateSecondarySummaryPage,
                            modifier = Modifier.padding(
                                start = 24.dp,
                                end = 24.dp,
                                top = 40.dp,
                                bottom = 34.dp
                            )
                        )
                    }

                    7 -> {
                        SecondarySummaryPage(
                            modifier = Modifier.padding(
                                start = 24.dp,
                                end = 24.dp,
                                top = 40.dp,
                                bottom = 34.dp
                            ),
                            navigateReviewShareCard = navigateReviewShareCard
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun Topic(
    totalNum: Int,
    pageNum: Int,
    onClickBackButton: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxWidth()
    ) {
        if (pageNum > 0) {
            IconButton(
                onClick = onClickBackButton,
                modifier = Modifier.padding(start = 24.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_pre_arrow_white),
                    contentDescription = "close",
                    tint = Color.Unspecified
                )
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            val topic = when (pageNum) {
                0 -> {
                    stringResource(id = R.string.topic_weather)
                }

                1 -> {
                    stringResource(id = R.string.topic_date)
                }

                2 -> {
                    stringResource(id = R.string.topic_partner)
                }

                3 -> {
                    stringResource(id = R.string.topic_group_solo)
                }

                4 -> {
                    stringResource(id = R.string.topic_group_solo)
                }

                5 -> {
                    stringResource(id = R.string.topic_taste)
                }

                6 -> {
                    stringResource(id = R.string.topic_feeling)
                }

                7 -> {
                    stringResource(id = R.string.topic_summary)
                }

                else -> {
                    ""
                }
            }

            Text(
                text = topic, style = ProofTheme.typography.headingL,
                textAlign = TextAlign.Center,
                color = ProofTheme.color.white,
            )
        }

    }
}

@Preview
@Composable
fun ReviewWriteScreenWithBottomSheet() {
//    MaterialTheme {
//        ReviewWriteScreen()
//    }
}