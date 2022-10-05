package com.mashup.zuzu.ui.review

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mashup.zuzu.R
import com.mashup.zuzu.compose.component.TasteColumn
import com.mashup.zuzu.compose.component.WineImageCardForReviewWrite
import com.mashup.zuzu.compose.theme.ProofTheme
import kotlinx.coroutines.launch

@Composable
fun ReviewWriteRoute(
    viewModel: ReviewWriteViewModel = hiltViewModel(),
    navigateReviewShareCard: (Long) -> Unit,
    navigateBack: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    val selectedListState by viewModel.selectedList.collectAsState()
    val currentIndexState by viewModel.currentIndex.collectAsState()
    val isSelectableListState = viewModel.isSelectableList

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
            val result = viewModel.navigateReviewShareCard(place, pairing, navigateReviewShareCardScreen = navigateReviewShareCard)
        },
        navigateBack = navigateBack,
        tasteUiState = TasteUiState(
            radioTitles = viewModel.radioTitles,
            selectedList = selectedListState,
            currentIndex = currentIndexState,
            radioButtons = viewModel.radioButtons
        ),
        updateSelectedList = viewModel::updateSelectedList,
        updateCurrentIndex = viewModel::updateCurrentIndex,
        updateSelectState = viewModel::updateSelectState,
        isSelectableListState = isSelectableListState
    )
}

@OptIn(ExperimentalMaterialApi::class, ExperimentalComposeUiApi::class)
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
    navigateReviewShareCard: (String, List<String>) -> Unit,
    navigateBack: () -> Unit,
    tasteUiState: TasteUiState,
    updateCurrentIndex: (Int) -> Unit,
    updateSelectedList: (Int, Int) -> Unit,
    updateSelectState: (Int) -> Unit,
    isSelectableListState: List<Boolean>
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
                        onClick = {
                            coroutineScope.launch {
                                bottomSheetScaffoldState.bottomSheetState.collapse()
                            }
                        },
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
                        onClick = {
                            coroutineScope.launch {
                                navigateBack()
                            }
                        },
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
        },
        sheetPeekHeight = 0.dp,
        topBar = {
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
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 44.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LazyColumn(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                item {
                    WineImageCardForReviewWrite(
                        wineImageUrl = uiState.wineImageUrl,
                        wineName = uiState.wineName,
                        modifier = Modifier
                            .width(148.dp)
                            .height(190.dp)
                    )

                    Topic(
                        totalNum = uiState.page,
                        pageNum = uiState.page,
                        onClickBackButton = navigatePreviousWritePage,
                        modifier = Modifier.wrapContentHeight().padding(top = 40.dp, bottom = 34.dp)
                    )
                }

                when (uiState.page) {
                    0 -> {
                        item {
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
                    }

                    1 -> {
                        item {
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
                    }

                    2 -> {
                        item {
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
                    }

                    3 -> {
                        item {
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
                    }

                    4 -> {
                        item {
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
                    }

                    5 -> {
                        item {
                            TasteColumn(
                                navigateSummaryPage = navigateSummaryPage,
                                modifier = Modifier.padding(
                                    top = 40.dp,
                                    bottom = 34.dp
                                ),
                                selectedList = tasteUiState.selectedList,
                                currentIndex = tasteUiState.currentIndex,
                                radioTitles = tasteUiState.radioTitles,
                                radioButtons = tasteUiState.radioButtons,
                                updateCurrentIndex = updateCurrentIndex,
                                updateSelectedList = updateSelectedList
                            )
                        }
                    }

                    6 -> {
                        item {
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
                    }

                    7 -> {
                        item {
                            SecondarySummaryPage(
                                modifier = Modifier.padding(
                                    start = 24.dp,
                                    end = 24.dp,
                                    top = 40.dp,
                                    bottom = 34.dp
                                ),
                                navigateReviewShareCard = navigateReviewShareCard,
                                updateSelectState = updateSelectState,
                                isSelectableListState = isSelectableListState
                            )
                        }
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
                modifier = Modifier.padding(end = 40.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_pre_arrow_white),
                    contentDescription = "close",
                    tint = Color.Unspecified
                )
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center),
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
                text = topic,
                style = ProofTheme.typography.headingL,
                textAlign = TextAlign.Center,
                color = ProofTheme.color.white
            )
        }
    }
}
