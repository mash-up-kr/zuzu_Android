package com.mashup.zuzu.data.response.model

import com.google.gson.annotations.SerializedName

data class IsHeavy(

    @SerializedName("Heavy")
    val Heavy: Int,

    @SerializedName("Light")
    val Light: Int
)
