package com.mashup.zuzu.data.response

import com.mashup.zuzu.data.response.model.Review
import com.mashup.zuzu.data.response.model.Wine

data class GetReviewWithIdResponse(
    val drink: Wine,
    val userReview: Review
)