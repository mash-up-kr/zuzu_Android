package com.mashup.zuzu.data.response.model

import com.google.gson.annotations.SerializedName

/**
 * @Created by 김현국 2022/08/11
 */

data class Profile(

    @SerializedName("id")
    val id: Long,

    @SerializedName("imageUrl")
    val image_url: String
)
