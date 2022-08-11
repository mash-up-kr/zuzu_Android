package com.mashup.zuzu.data.mapper

import com.mashup.zuzu.data.model.Wine
import com.mashup.zuzu.data.response.GetDrinksResponse

/**
 * @Created by 김현국 2022/08/08
 * GetDrinksResposne(
val id: String,
val createdAt: String,
val updatedAt: String,
val deletedAt: String?,
val name: String,
val alc: Float,
val origin: String,
val description: String,
val image_url: String
 * )
 */

fun wineResponseToModel(getDrinksResponse: List<GetDrinksResponse>): List<Wine> {
    return getDrinksResponse.map { getDrinksResponse ->
        Wine(
            id = getDrinksResponse.id,
            name = getDrinksResponse.name,
            imageUrl = getDrinksResponse.image_url,
            alc = getDrinksResponse.alc.toInt(),
            tags = emptyList(),
            description = getDrinksResponse.description,
            favorite = false,
            category = nameToKorean(getDrinksResponse.category.name)
        )
    }
}
