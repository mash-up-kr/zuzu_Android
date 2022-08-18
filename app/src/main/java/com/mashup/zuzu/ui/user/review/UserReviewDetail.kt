package com.mashup.zuzu.ui.user.review

import android.graphics.Bitmap
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import com.mashup.zuzu.R
import com.mashup.zuzu.compose.component.Button
import com.mashup.zuzu.compose.component.HorizontalPagerWithCapture
import com.mashup.zuzu.compose.theme.ProofTheme
import com.mashup.zuzu.data.model.wines
import dev.shreyaspatil.capturable.controller.rememberCaptureController

/**
 * @Created by 김현국 2022/08/11
 */

@Composable
fun UserReviewDetailRoute(
    viewModel: UserReviewDetailViewModel,
    onClick: (UserReviewDetailUiEvents) -> Unit
) {
    UserReviewDetailScreen(
        onClick = onClick
    )
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun UserReviewDetailScreen(
    modifier: Modifier = Modifier.fillMaxSize(),
    onClick: (UserReviewDetailUiEvents) -> Unit
) {
    val pagerState = rememberPagerState()
    val captureController = rememberCaptureController()

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        UserReviewDetailTopBar(
            modifier = Modifier
                .padding(start = 33.dp, end = 33.dp, top = 31.5.dp)
                .fillMaxWidth()
                .height(52.dp),
            onBackButtonClick = { onClick(UserReviewDetailUiEvents.BackButtonClick) },
            onEditReviewButtonClick = { onClick(UserReviewDetailUiEvents.EditReviewButtonClick(wines[pagerState.currentPage].id)) }
        )
        HorizontalPagerWithCapture(
            modifier = Modifier
                .fillMaxWidth()
                .height(445.dp),
            onWineBoardClick = {},
            wines = wines,
            pagerState = pagerState,
            childModifier = null,
            captureController = captureController
        )
        HorizontalPagerIndicator(
            pagerState = pagerState,
            modifier = Modifier.align(Alignment.CenterHorizontally)
                .padding(16.dp),
            activeColor = ProofTheme.color.gray50,
            inactiveColor = ProofTheme.color.gray400
        )
        Button(
            modifier = Modifier
                .padding(start = 33.dp, end = 33.dp, bottom = 84.dp)
                .fillMaxWidth()
                .height(52.dp),
            text = "이미지로 공유하기",
            backgroundColor = ProofTheme.color.primary300,
            textColor = ProofTheme.color.white,
            onButtonClick = { captureController.capture(Bitmap.Config.ARGB_8888) }
        )
    }
}

@Composable
fun UserReviewDetailTopBar(
    modifier: Modifier,
    onBackButtonClick: () -> Unit,
    onEditReviewButtonClick: () -> Unit
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier
                .clickable {
                    onBackButtonClick()
                },
            painter = painterResource(id = R.drawable.ic_arrow_left),
            tint = ProofTheme.color.white,
            contentDescription = null
        )
        Text(
            text = "리뷰 상세",
            style = ProofTheme.typography.headingXS,
            color = ProofTheme.color.white
        )
        Icon(
            modifier = Modifier
                .width(18.62.dp)
                .height(18.62.dp)
                .clickable {
                    onEditReviewButtonClick()
                },
            painter = painterResource(id = R.drawable.ic_edit),
            tint = ProofTheme.color.primary200,
            contentDescription = null
        )
    }
}

@Preview
@Composable
fun TopBarPreview() {
    ProofTheme {
        UserReviewDetailTopBar(
            modifier = Modifier
                .padding(start = 33.dp, end = 33.dp)
                .fillMaxWidth()
                .height(52.dp),
            {},
            {}
        )
    }
}

@Preview
@Composable
fun PreviewUserReviewDetailScreen() {
    ProofTheme {
        UserReviewDetailScreen(onClick = {
            when (it) {
            }
        })
    }
}
