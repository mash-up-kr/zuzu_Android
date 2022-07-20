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
import com.mashup.zuzu.data.model.OptionWithEmoji
import com.mashup.zuzu.ui.theme.ProofTheme

@Composable
fun ReviewWriteRoute(
    viewModel: ReviewWriteViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    ReviewWriteScreen(
        uiState = uiState,
        navigatePreviousWritePage = viewModel::navigatePreviousWritePage,
        navigateTimeSelectPage = viewModel::navigateTimeSelectPage,
        navigatePartnerPage = viewModel::navigatePartnerPage
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ReviewWriteScreen(
    uiState: ReviewWriteUiState,
    navigatePreviousWritePage: () -> Unit,
    navigateTimeSelectPage: (String) -> Unit,
    navigatePartnerPage: (String) -> Unit
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
                        onClick = { /*TODO*/ },
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
                    topic = uiState.selectOption?.topic ?: "",
                    totalNum = uiState.page,
                    pageNum = uiState.page,
                    onClickBackButton = navigatePreviousWritePage
                )
            }

            //TODO : 중복 코드 제거 및 추상화가 필요함
            uiState.selectOption?.let {
                when (uiState.page) {
                    0 -> {
                        OptionWithFourString(
                            options = uiState.selectOption.options.map { it as OptionWithEmoji } ,
                            onClickOption = navigateTimeSelectPage
                        )
                    }

                    1 -> {
                        OptionWithFourString(
                            options = uiState.selectOption.options.map { it as OptionWithEmoji } ,
                            onClickOption = navigatePartnerPage
                        )
                    }

                    2 -> {
                        OptionWithFourString(
                            options = uiState.selectOption.options.map { it as OptionWithEmoji } ,
                            onClickOption = navigateTimeSelectPage
                        )
                    }

                    3 -> {

                    }

                    4 -> {

                    }

                    5 -> {

                    }

                    else -> {
                        // error 처리
                    }
                }
            }
        }
    }
}

@Composable
fun Topic(
    topic: String,
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
            Text(
                text = topic, style = ProofTheme.typography.headingL,
                textAlign = TextAlign.Center,
                color = ProofTheme.color.white,
            )
        }

    }


}

@Composable
fun OptionWithFourString(
    options: List<OptionWithEmoji>,
    onClickOption: (String) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(horizontal = 34.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(options) { option ->
            Button(
                onClick = {
                    onClickOption(option.content)
                },
                colors = ButtonDefaults.buttonColors(backgroundColor = ProofTheme.color.gray600),
                modifier = Modifier.height(52.dp)
            ) {
                if (!option.emoji.isNullOrEmpty()) {
                    //TODO: 이모지 불러오기 필요
                }

                Text(
                    text = option.content,
                    style = ProofTheme.typography.headingXS,
                    color = ProofTheme.color.white
                )
            }
        }
    }
}

@Composable
fun OptionWithGroup() {

}

@Composable
fun OptionWithSolo() {

}


@Composable
fun OptionWithToggle() {

}


@Preview
@Composable
fun ReviewWriteScreenWithBottomSheet() {
//    MaterialTheme {
//        ReviewWriteScreen()
//    }
}