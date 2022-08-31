package com.mashup.zuzu.data.mapper

import com.mashup.zuzu.data.model.Wine
import com.mashup.zuzu.data.model.WineWithCategoryModel
import com.mashup.zuzu.data.response.GetDrinksWithCategoryResponse
import com.mashup.zuzu.data.response.model.Wine as ResponseWine
/**
 * @Created by 김현국 2022/08/08
 */

fun responseWineModelToDataModel(
    wine: ResponseWine
): Wine {
    return Wine(
        id = wine.id,
        name = wine.name,
        imageUrl = wine.image_url,
        alc = wine.alc,
        tags = if (wine.tags == null) {
            emptyList()
        } else {
            wine.tags.map {
                mapperResultToKorean(it)
            }
        },
        description = wine.description,
        category = nameToKorean(wine.category),
        worldcupWinCount = wine.worldcupWinCount,
        worldcupSemiFinalCount = wine.worldcupSemiFinalCount,
        origin = wine.origin,
        bitmap = null
    )
}

fun responseWineListModelToListDataModel(
    wineList: List<ResponseWine>
): List<Wine> {
    return wineList.map { wine ->
        Wine(
            id = wine.id,
            name = wine.name,
            imageUrl = wine.image_url,
            alc = wine.alc,
            tags = if (wine.tags == null) {
                emptyList()
            } else {
                wine.tags.map {
                    mapperResultToKorean(it)
                }
            },
            description = wine.description,
            category = nameToKorean(wine.category),
            worldcupWinCount = wine.worldcupWinCount,
            worldcupSemiFinalCount = wine.worldcupSemiFinalCount,
            origin = wine.origin,
            bitmap = null
        )
    }
}

fun wineWithCategoryResponseToModel(
    getDrinksCategoryResponse: GetDrinksWithCategoryResponse
): WineWithCategoryModel {
    return WineWithCategoryModel(
        total_page = getDrinksCategoryResponse.totalPageCount,
        wines = getDrinksCategoryResponse.wineList.map { wine ->
            responseWineModelToDataModel(wine)
        }
    )
}
