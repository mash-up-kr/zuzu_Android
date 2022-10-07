package com.mashup.zuzu.ui.user.review

import android.graphics.Bitmap
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState
import com.mashup.zuzu.R
import com.mashup.zuzu.compose.component.Button
import com.mashup.zuzu.compose.component.HorizontalPagerWithCapture
import com.mashup.zuzu.compose.component.PagerIndicator
import com.mashup.zuzu.compose.theme.ProofTheme
import com.mashup.zuzu.data.model.ReviewShareCards
import dev.shreyaspatil.capturable.controller.rememberCaptureController

/**
 * @Created by 김현국 2022/08/11
 */

@Composable
fun UserReviewDetailRoute(
    viewModel: UserReviewDetailViewModel,
    onClick: (UserReviewDetailUiEvents) -> Unit
) {
    val userReviewState = viewModel.userReviewList.collectAsState()
    val blurBitmap by viewModel.bitmap.collectAsState()
    when (userReviewState.value) {
        is UserReviewsDetailUiState.Loading -> {
        }
        is UserReviewsDetailUiState.Success -> {
            UserReviewDetailScreen(
                onClick = onClick,
                reviews = (userReviewState.value as UserReviewsDetailUiState.Success).reviews,
                bitmap = blurBitmap
            )
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun UserReviewDetailScreen(
    modifier: Modifier = Modifier.fillMaxSize(),
    reviews: ReviewShareCards,
    onClick: (UserReviewDetailUiEvents) -> Unit,
    bitmap: Bitmap?
) {
    val pagerState = rememberPagerState()
    val captureController = rememberCaptureController()

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        UserReviewDetailTopBar(
            modifier = Modifier
                .padding(start = 33.dp, end = 33.dp, top = 31.5.dp)
                .fillMaxWidth()
                .height(52.dp),
            onBackButtonClick = { onClick(UserReviewDetailUiEvents.BackButtonClick) },
            onEditReviewButtonClick = { onClick(UserReviewDetailUiEvents.EditReviewButtonClick(reviews.wine.id)) }
        )

        HorizontalPagerWithCapture(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(max = 530.dp),
            reviews = reviews,
            pagerState = pagerState,
            childModifier = null,
            captureController = captureController
        )

        PagerIndicator(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            pagerState = pagerState,
            indicatorCount = if (reviews.userReviews.size >= 5) 5 else reviews.userReviews.size,
            activeColor = ProofTheme.color.gray50,
            inActiveColor = ProofTheme.color.gray400
        )
        Button(
            modifier = Modifier
                .padding(start = 24.dp, end = 24.dp)
                .fillMaxWidth()
                .height(54.dp),
            text = "이미지로 공유하기",
            backgroundColor = ProofTheme.color.primary300,
            textColor = ProofTheme.color.white,
            textStyle = ProofTheme.typography.buttonL,
            onButtonClick = { captureController.capture(Bitmap.Config.ARGB_8888) }
        )
        Spacer(
            modifier = Modifier.heightIn(min = 0.dp, max = 16.dp)
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
            tint = ProofTheme.color.primary50,
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
