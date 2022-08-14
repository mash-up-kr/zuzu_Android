package com.mashup.zuzu.data.mapper

import com.mashup.zuzu.data.model.Category
import com.mashup.zuzu.data.response.GetDrinksCategoryResponse

/**
 * @Created by 김현국 2022/08/08
 */

fun categoryResponseToModel(
    categoryResponse: List<GetDrinksCategoryResponse>
): List<Category> {
    return categoryResponse.map { getDrinksCategoryResponse ->
        Category(
            imageUrl = getDrinksCategoryResponse.image_url,
            title = nameToKorean(getDrinksCategoryResponse.name)
        )
    }
}

fun nameToKorean(
    categoryName: String
): String {
    return when (categoryName) {
        "All" -> "전체"
        "Beer" -> "맥주"
        "Whisky" -> "위스키"
        "Wine" -> "와인"
        "Cocktail" -> "칵테일"
        "Traditional" -> "전통주"
        "Soju" ->  "소주"
        else -> "기타"
    }
}

fun nameToEnglish(
    categoryName: String
): String {
    return when (categoryName) {
        "전체" -> "All"
        "맥주" -> "Beer"
        "위스키" -> "Whisky"
        "와인" -> "Wine"
        "칵테일" -> "Cocktail"
        "소주" -> "Soju"
        "전통주" -> "Traditional"
        else -> "Etc"
    }
}
