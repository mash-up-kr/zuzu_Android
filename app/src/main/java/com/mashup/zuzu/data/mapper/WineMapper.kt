package com.mashup.zuzu.data.mapper

import com.mashup.zuzu.data.model.Wine
import com.mashup.zuzu.data.model.WineWithCategoryModel
import com.mashup.zuzu.data.response.GetDrinksResponse
import com.mashup.zuzu.data.response.GetDrinksWithCategoryResponse
import com.mashup.zuzu.data.response.GetDrinksWithIdResponse
import com.mashup.zuzu.data.response.model.Wine as ResponseWine
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

fun wineListResponseToModel(getDrinksResponse: List<GetDrinksResponse>): List<Wine> {
    return getDrinksResponse.map { getDrinksResponse ->
        Wine(
            id = getDrinksResponse.id,
            name = getDrinksResponse.name,
            imageUrl = getDrinksResponse.image_url,
            alc = getDrinksResponse.alc.toInt(),
            tags = emptyList(),
            description = getDrinksResponse.description,
            category = nameToKorean(getDrinksResponse.category.name)
        )
    }
}

fun wineResponseToModel(
    getDrinksWithIdResponse: GetDrinksWithIdResponse
): Wine {
    return Wine(
        id = getDrinksWithIdResponse.id,
        name = getDrinksWithIdResponse.name,
        imageUrl = getDrinksWithIdResponse.image_url,
        alc = getDrinksWithIdResponse.alc.toInt(),
        tags = emptyList(),
        description = getDrinksWithIdResponse.description,
        category = nameToKorean(getDrinksWithIdResponse.category.name))
}

fun wineWithCategoryResponseToModel(
    getDrinksCategoryResponse: GetDrinksWithCategoryResponse
): WineWithCategoryModel {
    return WineWithCategoryModel(
        total_page = getDrinksCategoryResponse.totalPageCount,
        wines = getDrinksCategoryResponse.wineList.map { wine ->
            wineResponseModelToDataModel(wine)
        }
    )
}

fun wineResponseModelToDataModel(
    wine: ResponseWine
): Wine {
    return Wine(
        id = wine.id,
        name = wine.name,
        imageUrl = wine.image_url,
        alc = wine.alc.toInt(),
        tags = emptyList(),
        description = wine.description,
        category = nameToKorean(wine.category.name)
    )
}
