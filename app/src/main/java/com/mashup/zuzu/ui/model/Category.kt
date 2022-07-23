package com.mashup.zuzu.ui.model

/**
 * @Created by 김현국 2022/07/20
 * @Time 4:25 오후
 */

data class Category(
    val imageText: String,
    val title: String
)

val categoryList = listOf(
    Category(imageText = "\uD83C\uDF7C", title = "전체"),
    Category(imageText = "\uD83C\uDF7A", title = "맥주"),
    Category(imageText = "\uD83E\uDD43", title = "위스키"),
    Category(imageText = "\uD83E\uDD43", title = "와인"),
    Category(imageText = "\uD83C\uDF78", title = "칵테일"),
)