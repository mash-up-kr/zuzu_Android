package com.mashup.zuzu.data.request

data class ReviewWriteRequest(
    val weather: String = "Rainy",
    val time: String = "Noon",
    val companion: String = "Alone",
    val mood: String = "Funny",
    val spot: Int? = null,
    val isHeavy: Int = 1,
    val isBitter: Int = 1,
    val isStrong: Int = 1,
    val isBurning: Int = 1,
    val taste: String = "Fruity",
    val place: String = "와인 한잔",
    val pairing: List<String> = listOf("Grilled", "Fried"),
)
