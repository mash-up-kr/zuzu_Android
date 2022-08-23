package com.mashup.zuzu.data.response.model

import com.google.gson.annotations.SerializedName

data class Result(

    @SerializedName("isBitter")
    val isBitter: IsBitter,

    @SerializedName("isBurning")
    val isBurning: IsBurning,

    @SerializedName("isHeavy")
    val isHeavy: IsHeavy,

    @SerializedName("isStrong")
    val isStrong: IsStrong,

    @SerializedName("pairing")
    val pairing: List<String>,

    @SerializedName("situation")
    val situation: List<String>,

    @SerializedName("taste")
    val taste: List<Taste>
)
