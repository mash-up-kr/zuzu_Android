package com.mashup.zuzu.ui.user.review

/**
 * @Created by 김현국 2022/08/11
 */
sealed class UserReviewDetailUiEvents {
    object BackButtonClick : UserReviewDetailUiEvents()

    data class ShareImageButtonClick(
        val imageCard: Int
    ) : UserReviewDetailUiEvents()
}
