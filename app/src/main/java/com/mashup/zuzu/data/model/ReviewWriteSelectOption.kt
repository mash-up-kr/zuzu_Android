package com.mashup.zuzu.data.model

/**
 * 리뷰 작성 페이지에 포함되는 데이터를 나타낸다.
 */
data class ReviewWriteSelectOption(
    val topic: String,
    val options: List<SelectOption>
)

interface SelectOption

/**
 * 이모지가 포함될 수 있는 선택 옵션을 나타낸다.
 */
data class OptionWithEmoji(
    val emoji: String?,
    val content: String
): SelectOption

data class OptionWithToggle(
    val empty: String
): SelectOption

data class OptionWithImage(
    val url: String,
    val content: String
): SelectOption