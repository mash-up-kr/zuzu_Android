package com.mashup.zuzu.data.response.model

import com.google.gson.annotations.SerializedName

/**
 * @Created by 김현국 2022/08/22
 */
data class Round(

    @SerializedName("count")
    val count: Int,

    @SerializedName("title")
    val title: String
)
