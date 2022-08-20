package com.mashup.zuzu.data.request

data class ReviewWriteRequest(
    val weather: String = "Rainy",
    val time: String = "Noon",
    val companion: String = "Alone",
    val mood: String = "Funny",
    val spot: Int? = null,
    val is_heavy: Int = 1,
    val is_bitter: Int = 1,
    val is_strong: Int = 1,
    val is_burning: Int = 1,
    val taste: String = "Fruity",
    val place: String = "와인 한잔",
    val pairing: List<String> = listOf("Grilled", "Fried"),
)
