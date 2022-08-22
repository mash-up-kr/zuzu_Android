package com.mashup.zuzu.ui.user.review

import com.mashup.zuzu.data.model.ReviewShareCards

/**
 * @Created by 김현국 2022/08/20
 */

sealed class UserReviewsDetailUiState {

    object Loading : UserReviewsDetailUiState()

    data class Success(val reviews: ReviewShareCards) : UserReviewsDetailUiState()
}
