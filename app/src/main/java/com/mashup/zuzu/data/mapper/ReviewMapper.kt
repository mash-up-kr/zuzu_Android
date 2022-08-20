package com.mashup.zuzu.data.mapper

import com.mashup.zuzu.data.model.ReviewShareCard
import com.mashup.zuzu.data.model.ReviewShareCards
import com.mashup.zuzu.data.model.UserReview
import com.mashup.zuzu.data.response.GetReviewsDrinksResponse
import com.mashup.zuzu.data.response.ReviewList

/**
 * @Created by 김현국 2022/08/20
 */

fun reviewsDrinksResponseToModel(getReviewsDrinksResponse: GetReviewsDrinksResponse): ReviewShareCards {
    return ReviewShareCards(
        wine = responseWineModelToDataModel(getReviewsDrinksResponse.drink),
        userReviews = reviewListResponseToModel(getReviewsDrinksResponse.reviewList)
    )
}

fun reviewListResponseToModel(reviewList: List<ReviewList>): List<UserReview> {
    return reviewList.map { reviewList: ReviewList ->
        UserReview(
            id = reviewList.id,
            createdAt = reviewList.createdAt,
            mood = reviewList.mood,
            weather = reviewList.weather,
            time = reviewList.time,
            isHeavy = reviewList.is_heavy,
            isBitter = reviewList.is_bitter,
            isStrong = reviewList.is_strong,
            isBurning = reviewList.is_burning,
            taste = reviewList.taste
        )
    }
}

fun reviewShareCardToListModel(reviewShareCards: ReviewShareCards, index: Int): ReviewShareCard {
    return ReviewShareCard(
        wine = reviewShareCards.wine,
        userReview = reviewShareCards.userReviews[index]

    )
}
