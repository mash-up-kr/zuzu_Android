package com.mashup.zuzu.data.response.model

import com.google.gson.annotations.SerializedName

data class IsBitter(

    @SerializedName("Bitter")
    val Bitter: Int,

    @SerializedName("Sweet")
    val Sweet: Int
)
