package com.mashup.zuzu.data.response

import com.google.gson.annotations.SerializedName
import com.mashup.zuzu.data.response.model.Wine

/**
 * @Created by 김현국 2022/08/08
 */
data class GetDrinksWithCategoryResponse(

    @SerializedName("totalPageCount")
    val totalPageCount: Int,

    @SerializedName("list")
    val wineList: List<Wine>
)
