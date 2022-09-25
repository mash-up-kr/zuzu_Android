package com.mashup.zuzu.ui.review

data class ReviewWriteUiState(
    val page: Int,
    val wineImageUrl: String,
    val wineName: String
)

data class TasteUiState(
    val radioTitles: List<Pair<String, String>>,
    val selectedList: List<Int>,
    val currentIndex: Int,
    val radioButtons: List<Pair<Int, Int>>
)
