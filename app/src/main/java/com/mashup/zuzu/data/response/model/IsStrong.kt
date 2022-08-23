package com.mashup.zuzu.data.response.model

import com.google.gson.annotations.SerializedName

data class IsStrong(

    @SerializedName("Mild")
    val Mild: Int,

    @SerializedName("Strong")
    val Strong: Int
)
