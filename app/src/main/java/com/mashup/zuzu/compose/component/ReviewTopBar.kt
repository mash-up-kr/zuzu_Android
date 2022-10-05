package com.mashup.zuzu.compose.component

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.mashup.zuzu.R
import com.mashup.zuzu.ui.review.ReviewDetailUiEvents

/**
 * @Created by 김현국 2022/09/26
 */

@Composable
fun ReviewTopBar(
    scrollState: LazyListState,
    onClick: (ReviewDetailUiEvents) -> Unit
) {
    val isVisible by remember {
        derivedStateOf {
            scrollState.firstVisibleItemIndex
        }
    }
    Row(
        modifier = Modifier.fillMaxWidth().height(52.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Crossfade(
            targetState = isVisible
        ) { isVisible ->
            if (isVisible > 0) {
                Image(
                    modifier = Modifier.padding(start = 33.dp).clickable {
                        onClick(ReviewDetailUiEvents.BackButtonClick)
                    },
                    painter = painterResource(id = R.drawable.ic_arrow_left),
                    contentDescription = null
                )
            } else {
                Image(
                    modifier = Modifier.padding(start = 33.dp).clickable {
                        onClick(ReviewDetailUiEvents.BackButtonClick)
                    },
                    painter = painterResource(id = R.drawable.ic_arrow_left_gray),
                    contentDescription = null
                )
            }
        }
    }
}
