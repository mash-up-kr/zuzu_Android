package com.mashup.zuzu.compose.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import com.google.accompanist.pager.*
import com.mashup.zuzu.data.model.Wine
import com.mashup.zuzu.data.model.wines
import kotlin.math.absoluteValue

/**
 * @Created by 김현국 2022/07/26
 */

@OptIn(ExperimentalPagerApi::class)
@Composable
fun HorizontalPagerWithOffsetTransitionWithPage(
    modifier: Modifier,
    onWineBoardClick: (Wine) -> Unit,
    wineList: List<Wine>,
    childModifier: Modifier?
) {
    HorizontalPager(
        count = wineList.size,
        // Add 32.dp horizontal padding to 'center' the pages
        contentPadding = PaddingValues(horizontal = 50.dp), // 양옆 패팅
        modifier = modifier
    ) { page ->

        PagerWineCard(
            modifier = Modifier
                .graphicsLayer {
                    // Calculate the absolute offset for the current page from the
                    // scroll position. We use the absolute value which allows us to mirror
                    // any effects for both directions
                    val pageOffset = calculateCurrentOffsetForPage(page).absoluteValue

                    // We animate the scaleX + scaleY, between 85% and 100% // 가운데에 오면 아이템이 커짐
                    lerp(
                        start = 0.85f,
                        stop = 1f,
                        fraction = 1f - pageOffset.coerceIn(0f, 1f)
                    ).also { scale ->
                        scaleX = scale
                        scaleY = scale
                    }

                    // We animate the alpha, between 50% and 100%
                    alpha = lerp(
                        start = 0.5f,
                        stop = 1f,
                        fraction = 1f - pageOffset.coerceIn(0f, 1f)
                    )
                }
                .width(262.dp).height(415.dp).clip(RoundedCornerShape(6.dp)),
            wine = wineList[page],
            onWineBoardClick = { onWineBoardClick(it) },
            childModifier = childModifier
        )
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun HorizontalPagerWithOffsetTransition(
    modifier: Modifier,
    onWineBoardClick: (Wine) -> Unit,
    wines: List<Wine>,
    pagerState: PagerState,
    childModifier: Modifier?
) {
    HorizontalPager(
        count = wines.size,
        state = pagerState,
        // Add 32.dp horizontal padding to 'center' the pages
        contentPadding = PaddingValues(horizontal = 50.dp), // 양옆 패팅
        modifier = modifier
    ) { page ->

        PagerWineCard(
            modifier = Modifier
                .graphicsLayer {
                    // Calculate the absolute offset for the current page from the
                    // scroll position. We use the absolute value which allows us to mirror
                    // any effects for both directions
                    val pageOffset = calculateCurrentOffsetForPage(page).absoluteValue

                    // We animate the scaleX + scaleY, between 85% and 100% // 가운데에 오면 아이템이 커짐
                    lerp(
                        start = 0.85f,
                        stop = 1f,
                        fraction = 1f - pageOffset.coerceIn(0f, 1f)
                    ).also { scale ->
                        scaleX = scale
                        scaleY = scale
                    }

                    // We animate the alpha, between 50% and 100%
                    alpha = lerp(
                        start = 0.5f,
                        stop = 1f,
                        fraction = 1f - pageOffset.coerceIn(0f, 1f)
                    )
                }
                .width(262.dp).height(415.dp).clip(RoundedCornerShape(12.dp)),
            wine = wines[page],
            onWineBoardClick = { onWineBoardClick(it) },
            childModifier = childModifier
        )
    }
}
