package com.mashup.zuzu.data.response

import com.google.gson.annotations.SerializedName
import com.mashup.zuzu.data.response.model.Review
import com.mashup.zuzu.data.response.model.Wine

/**
 * @Created by 김현국 2022/08/20
 */
data class GetReviewsDrinksResponse(
    @SerializedName("totalPageCount")
    val totalPageCount: Int,

    @SerializedName("drink")
    val drink: Wine,

    @SerializedName("reviewList")
    val reviewList: List<Review>

)
