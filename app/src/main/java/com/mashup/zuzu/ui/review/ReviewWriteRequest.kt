package com.mashup.zuzu.ui.review

//TODO: data Layer로 옮길 것
data class ReviewWriteRequest(
    val weather: String = "Rainy",
    val time: String = "Noon",
    val companion: String = "Alone",
    val mood: String = "Funny",
    val spot: Int = 1,
    val light: Int = 1,
    val sweet: Int = 1,
    val mild: Int = 1,
    val smooth: Int = 1,
    val taste: String = "Fruity",
    val place: String = "와인 한잔",
    val pairing: List<String> = listOf("Grilled", "Fried"),
)
