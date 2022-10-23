package com.mashup.zuzu.data.model

import androidx.compose.runtime.Immutable

@Immutable
data class ReviewShareCard(
    val wine: Wine = wines[0],
    val userReview: UserReview = UserReview()
)

@Immutable
data class ReviewShareCards(
    val wine: Wine,
    val userReviews: List<UserReview>
)

data class UserReview(
    val id: Long = 2,
    val createdAt: String = "2022-08-12T23:21:49.246Z",
    val mood: String = "Funny",
    val weather: String = "Rainy",
    val time: String = "Noon",
    val isHeavy: String = "Heavy",
    val isBitter: String = "Bitter",
    val isStrong: String = "Strong",
    val isBurning: String = "Smooth",
    val taste: String = "Fruity"
)
