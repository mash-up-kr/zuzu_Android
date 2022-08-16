package com.mashup.zuzu.ui.user.review

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState
import com.mashup.zuzu.R
import com.mashup.zuzu.compose.component.Button
import com.mashup.zuzu.compose.component.HorizontalPagerWithOffsetTransition
import com.mashup.zuzu.compose.theme.ProofTheme
import com.mashup.zuzu.data.model.wines
import timber.log.Timber

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
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
//        val pagerState = rememberPagerState((wines.size / 2).toDouble().roundToInt())
        val pagerState = rememberPagerState()

        LaunchedEffect(pagerState.currentPage) {
            Timber.tag("page").d(pagerState.currentPage.toString())
            // index 는 0부터 시작함
        }
        UserReviewDetailTopBar(
            modifier = Modifier
                .padding(start = 33.dp, end = 33.dp, top = 31.5.dp)
                .fillMaxWidth()
                .height(52.dp),
            onBackButtonClick = { onClick(UserReviewDetailUiEvents.BackButtonClick) },
            onEditReviewButtonClick = { onClick(UserReviewDetailUiEvents.EditReviewButtonClick(wines[pagerState.currentPage].id)) }
        )
        HorizontalPagerWithOffsetTransition(
            modifier = Modifier
                .fillMaxWidth()
                .height(445.dp),
            onWineBoardClick = {},
            wines = wines,
            pagerState = pagerState,
            childModifier = null
        )
        Button(
            modifier = Modifier
                .padding(start = 33.dp, end = 33.dp, bottom = 84.dp)
                .fillMaxWidth()
                .height(52.dp),
            text = "이미지로 공유하기",
            backgroundColor = ProofTheme.color.primary300,
            textColor = ProofTheme.color.white,
            onButtonClick = { onClick(UserReviewDetailUiEvents.ShareImageButtonClick(pagerState.currentPage)) }
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
