package com.mashup.zuzu.data.response.model

import com.google.gson.annotations.SerializedName

data class IsBurning(

    @SerializedName("Burning")
    val Burning: Int,

    @SerializedName("Smooth")
    val Smooth: Int
)
