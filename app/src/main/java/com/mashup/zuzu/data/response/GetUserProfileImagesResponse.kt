package com.mashup.zuzu.data.response

import com.google.gson.annotations.SerializedName

/**
 * @Created by 김현국 2022/08/10
 */
data class GetUserProfileImagesResponse(

    @SerializedName("id")
    val id: Long,

    @SerializedName("imageUrl")
    val image: String
)
