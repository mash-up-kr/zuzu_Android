package com.mashup.zuzu.data.response

import com.google.gson.annotations.SerializedName

/**
 * @Created by 김현국 2022/08/08
 */
data class GetDrinksWithIdResponse(

    @SerializedName("id")
    val id: Long,

    @SerializedName("name")
    val name: String,

    @SerializedName("abv")
    val alc: Float,

    @SerializedName("origin")
    val origin: String,

    @SerializedName("description")
    val description: String,

    @SerializedName("image_url")
    val image_url: String,

    @SerializedName("category")
    val category: Category

)

data class Category(

    @SerializedName("name")
    val name: String
)