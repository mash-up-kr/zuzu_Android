package com.mashup.zuzu.data.model

data class ReviewOption(
    val topic: String,
    val options: List<SelectOption>
)

interface SelectOption

data class OptionWithEmoji(
    val emoji: String?,
    val content: String
): SelectOption