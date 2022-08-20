package com.mashup.zuzu.data.response

import com.google.gson.annotations.SerializedName
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
    val reviewList: List<ReviewList>

)

data class Drink(

    @SerializedName("name")
    val name: String,

    @SerializedName("image_url")
    val image_url: String,

    @SerializedName("abv")
    val alc: Float,

    @SerializedName("origin")
    val origin: String,

    @SerializedName("category")
    val category: String
)

data class ReviewList(

    @SerializedName("id")
    val id: Long,

    @SerializedName("createdAt")
    val createdAt: String,

    @SerializedName("mood")
    val mood: String,

    @SerializedName("weather")
    val weather: String,

    @SerializedName("time")
    val time: String,

    @SerializedName("is_heavy")
    val is_heavy: String,

    @SerializedName("is_bitter")
    val is_bitter: String,

    @SerializedName("is_strong")
    val is_strong: String,

    @SerializedName("is_burning")
    val is_burning: String,

    @SerializedName("taste")
    val taste: String
)
