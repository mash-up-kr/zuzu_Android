package com.mashup.zuzu.ui.review

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mashup.zuzu.ui.component.WineImageCardForReviewDetail
import com.mashup.zuzu.ui.theme.ProofTheme

@Composable
fun ReviewDetailRoute(
    viewModel: ReviewDetailViewModel,
    navigateBack : () -> Unit,
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
        WineInformation(content = reviewDetailUiState.wine.description[0])

        WorldCupInfo()

        WineByReview()

        Button(
            onClick = navigateToReviewWrite,
            colors = ButtonDefaults.buttonColors(backgroundColor = ProofTheme.color.primary300),
            modifier = Modifier.fillMaxWidth().height(52.dp)
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
fun WorldCupInfo() {
    Column(
        modifier = Modifier.padding(top = 40.dp, start = 24.dp, end = 24.dp)
    ) {
        Text(
            text = "월드컵에서는",
            style = ProofTheme.typography.headingM,
            color = ProofTheme.color.white
        )
    }
}

@Composable
fun WineByReview() {
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

        //TODO: 선택한 분위기 리스트 보여주기

        Text(
            text = "이렇게 표현했어요",
            style = ProofTheme.typography.headingS,
            color = ProofTheme.color.white,
            modifier = Modifier.padding(top = 40.dp)
        )

        //TODO: 선택한 맛 도표로 보여주기

        Text(
            text = "한마디로 말하면,",
            style = ProofTheme.typography.headingS,
            color = ProofTheme.color.white,
            modifier = Modifier.padding(top = 40.dp)
        )

    }
}
