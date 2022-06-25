package com.mashup.zuzu.data.model

import androidx.compose.runtime.Immutable
import com.mashup.zuzu.R

/**
 * @Created by 김현국 2022/06/22
 * @Time 5:57 오후
 */

@Immutable
data class Category(
    val categoryImg: Int,
    val categoryName: String
)

val categories = listOf(
    Category(
        categoryImg = R.drawable.worldcup,
        categoryName = "맥주"
    ),
    Category(
        categoryImg = R.drawable.worldcup,
        categoryName = "위스키"
    ),
    Category(
        categoryImg = R.drawable.worldcup,
        categoryName = "와인"
    ),
    Category(
        categoryImg = R.drawable.worldcup,
        categoryName = "칵테일"
    ),
    Category(
        categoryImg = R.drawable.worldcup,
        categoryName = "전통주"
    ),
    Category(
        categoryImg = R.drawable.worldcup,
        categoryName = "사케"
    )
)
object CategoryModel {
    fun getCategories(): List<Category> = categories
}
