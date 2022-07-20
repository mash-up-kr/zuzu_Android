package com.mashup.zuzu.ui.review

import com.mashup.zuzu.data.model.OptionWithEmoji
import com.mashup.zuzu.data.model.ReviewWriteSelectOption

data class ReviewWriteUiState(
    val page: Int,
    val wineImage: Int,
    val selectOption: ReviewWriteSelectOption? = null
)

sealed interface ReviewWriteType {
    val page: Int
    val wineImage: Int
    val topic: String

    data class ReviewWriteWithFourString(
        override val page: Int,
        override val wineImage: Int,
        override val topic: String,
        val options: List<OptionWithEmoji>
    ): ReviewWriteType

    data class ReviewWriteWithGroup(
        override val page: Int,
        override val wineImage: Int,
        override val topic: String,
        val subTopic: String,
        val firstOptions: List<String>,
        val secondOptions: List<String>
    ): ReviewWriteType

    data class ReviewWriteWithSolo(
        override val page: Int,
        override val wineImage: Int,
        override val topic: String,
        val options: List<String>
    ): ReviewWriteType

    data class ReviewWriteWithToggle(
        override val page: Int,
        override val wineImage: Int,
        override val topic: String,
        val toggleOptions: List<Int>
    ): ReviewWriteType
}
