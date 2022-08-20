package com.mashup.zuzu.data.response.model

import com.google.gson.annotations.SerializedName

/**
 * @Created by 김현국 2022/08/13
 */
data class Wine(

    @SerializedName("id")
    val id: Long,

    @SerializedName("name")
    val name: String,

    @SerializedName("imageUrl")
    val image_url: String,

    @SerializedName("abv")
    val alc: Float,

    @SerializedName("origin")
    val origin: String,

    @SerializedName("description")
    val description: String?,

    @SerializedName("category")
    val category: String,

    @SerializedName("worldcupWinCount")
    val worldcupWinCount: Int?,

    @SerializedName("worldcupSemiFinalCount")
    val worldcupSemiFinalCount: Int?

)
