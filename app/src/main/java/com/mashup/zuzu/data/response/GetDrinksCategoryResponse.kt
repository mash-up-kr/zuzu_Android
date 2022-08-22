package com.mashup.zuzu.data.response

import com.google.gson.annotations.SerializedName

/**
 * @Created by 김현국 2022/08/08
 */
data class GetDrinksCategoryResponse(
    @SerializedName("id")
    val id: Long,

    @SerializedName("createdAt")
    val createdAt: String,

    @SerializedName("updatedAt")
    val updatedAt: String,

    @SerializedName("deletedAt")
    val deletedAt: String?,

    @SerializedName("name")
    val name: String,

    @SerializedName("imageUrl")
    val image_url: String
)
