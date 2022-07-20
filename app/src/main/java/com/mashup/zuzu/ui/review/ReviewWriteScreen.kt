package com.mashup.zuzu.ui.review

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.mashup.zuzu.ui.theme.ProofTheme
import kotlinx.coroutines.launch

@Composable
fun ReviewWriteRoute(
    viewModel: ReviewWriteViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    ReviewWriteScreen(
        uiState = uiState,
        navigatePreviousWritePage = viewModel::navigatePreviousWritePage,
        navigateDateSelectPage = viewModel::navigateDateSelectPage,
        navigatePartnerPage = viewModel::navigatePartnerPage,
        navigateGroupPage = viewModel::navigateGroupPage,
        navigateSoloPage = viewModel::navigateSoloPage
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
) {
    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState()
    val coroutineScope = rememberCoroutineScope()

    BottomSheetScaffold(
        scaffoldState = bottomSheetScaffoldState,
        sheetShape = RoundedCornerShape(24.dp, 24.dp),
        backgroundColor = ProofTheme.color.black,
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
                )

                Text(
                    text = stringResource(R.string.review_cancel_content),
                    style = ProofTheme.typography.bodyM,
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
                            .padding(end = 6.dp)
                    ) {
                        Text(
                            text = stringResource(R.string.cancel),
                            style = ProofTheme.typography.buttonL
                        )
                    }
                    Button(
                        onClick = { /*TODO*/ },
                        colors = ButtonDefaults.buttonColors(backgroundColor = ProofTheme.color.primary300),
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 6.dp)
                    ) {
                        Text(
                            text = stringResource(R.string.confirm),
                            style = ProofTheme.typography.buttonL
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
                    progress = (uiState.page + 1) / 6f,
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
                .fillMaxHeight()
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
                if (uiState.wineImage != 0) {
                    Image(
                        painter = painterResource(id = uiState.wineImage),
                        contentDescription = "wineImage",
                        modifier = Modifier.padding(bottom = 40.dp)
                    )
                }

                Topic(
                    totalNum = uiState.page,
                    pageNum = uiState.page,
                    onClickBackButton = navigatePreviousWritePage
                )
            }

            when (uiState.page) {
                0 -> {
                    WeatherSelectOption(navigateDateSelectPage = navigateDateSelectPage)
                }

                1 -> {
                    DateSelectOption(navigatePartnerPage = navigatePartnerPage)
                }

                2 -> {
                    PartnerSelectOption(
                        navigateGroupPage = navigateGroupPage,
                        navigateSoloPage = navigateSoloPage
                    )
                }
            }
        }
    }
}

@Composable
fun Topic(
    totalNum: Int,
    pageNum: Int,
    onClickBackButton: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxWidth()
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
                    "이 술을 마셨던 날의\n날씨는..."
                }

                1 -> {
                    "이 술을 마셨던\n시간은..."
                }

                2 -> {
                    "누구와 마셨나요?"
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