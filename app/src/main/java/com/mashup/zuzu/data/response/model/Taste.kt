package com.mashup.zuzu.data.response.model

import com.google.gson.annotations.SerializedName

data class Taste(

    @SerializedName("percent")
    val percent: Int,

    @SerializedName("tasteName")
    val tasteName: String
)
