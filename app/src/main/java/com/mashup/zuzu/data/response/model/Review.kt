package com.mashup.zuzu.data.response.model

import com.google.gson.annotations.SerializedName

/**
 * @Created by 김현국 2022/08/23
 */
data class Review(

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

    @SerializedName("isHeavy")
    val is_heavy: String,

    @SerializedName("isBitter")
    val is_bitter: String,

    @SerializedName("isStrong")
    val is_strong: String,

    @SerializedName("isBurning")
    val is_burning: String,

    @SerializedName("taste")
    val taste: String
)
