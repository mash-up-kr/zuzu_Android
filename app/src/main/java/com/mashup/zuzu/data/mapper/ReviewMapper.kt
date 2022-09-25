package com.mashup.zuzu.data.mapper

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import com.mashup.zuzu.R
import com.mashup.zuzu.data.model.ReviewShareCard
import com.mashup.zuzu.data.model.ReviewShareCards
import com.mashup.zuzu.data.model.UserReview
import com.mashup.zuzu.data.response.GetReviewsDrinksResponse
import com.mashup.zuzu.data.response.model.Review
import com.mashup.zuzu.data.response.model.Taste

/**
 * @Created by 김현국 2022/08/20
 */

fun reviewsDrinksResponseToModel(getReviewsDrinksResponse: GetReviewsDrinksResponse): ReviewShareCards {
    return ReviewShareCards(
        wine = responseWineModelToDataModel(getReviewsDrinksResponse.drink),
        userReviews = reviewListResponseToModel(getReviewsDrinksResponse.reviewList)
    )
}

fun reviewListResponseToModel(reviewList: List<Review>): List<UserReview> {
    return reviewList.map { review: Review ->
        UserReview(
            id = review.id,
            createdAt = review.createdAt,
            mood = review.mood,
            weather = review.weather,
            time = review.time,
            isHeavy = review.is_heavy,
            isBitter = review.is_bitter,
            isStrong = review.is_strong,
            isBurning = review.is_burning,
            taste = review.taste
        )
    }
}

fun reviewShareCardToListModel(reviewShareCards: ReviewShareCards, index: Int): ReviewShareCard {
    return ReviewShareCard(
        wine = reviewShareCards.wine,
        userReview = reviewShareCards.userReviews[index]

    )
}

fun mapperResultToKorean(situation: String): String {
    return when (situation) {
        "Rainy" -> { "비오는 날" }
        "Snowy" -> { "눈오는 날" }
        "Sunny" -> { "맑은 날" }
        "Cloudy" -> { "흐린 날" }
        "Evening" -> { "저녁에" }
        "Noon" -> { "대낮에" }
        "Midnight" -> { "한밤중에" }
        "Dawn" -> { "새벽에" }
        "Alone" -> { "혼자서" }
        "Friends" -> { "친구/지인과" }
        "Lover" -> { "연인과" }
        "Gather" -> { "회식/단체 모임" }
        "Funny" -> { "친근한/웃기는" }
        "Serious" -> { "엄격진지한" }
        "Romantic" -> { "로맨틱한" }
        "Crazy" -> { "광란의" }
        "Gloomy" -> { "우울한" }
        "Celebratory" -> { "축하하는" }
        "Delight" -> { "즐거운" }
        "Enjoy" -> { "음미하는" }
        "1" -> { "1차" }
        "2" -> { "2차" }
        "3" -> { "3차" }
        else -> { situation }
    }
}

fun mapperTasteListToKorean(tasteList: List<Taste>): List<Taste> {
    return tasteList.map {
        val name = it.tasteName
        it.copy(
            tasteName = mapperTasteToKorean(name)
        )
    }
}
fun mapperTasteToKorean(taste: String): String {
    return when (taste) {
        "Fruity" -> { "상큼달달한 과일" }
        "Woody" -> { "묵직한 나무" }
        "Noroong" -> { "구수한 누룽지" }
        "Creamy" -> { "부드러운 우유" }
        "Earthy" -> { "쿰쿰함 흙냄새" }
        "Flower" -> { "향긋한 꽃" }
        "Austere" -> { "씁쓸한 인생" } "Chilli" -> { "매운 칠리" }
        "Unknown" -> { "잘 모르겠어요" }
        else -> { taste }
    }
}
fun mapperPairingToKorean(pairing: String): String {
    return when (pairing) {
        "Grilled" -> {
            "구이류"
        }
        "Fried" -> {
            "튀김/전"
        }
        "Cheeze" -> {
            "치즈"
        }
        "Salad" -> {
            "샐러드"
        }
        "Fruits" -> {
            "과일"
        }
        "Soup" -> {
            "국물/스프"
        }
        "AppetizersSnacks" -> {
            "디저트"
        }
        "Pasta" -> {
            "면류"
        }
        "Sushi" -> {
            "회/초밥"
        }

        else -> { "" }
    }
}

@Composable
fun mapperKoreanToPainter(pairing: String): Painter {
    return when (pairing) {
        "구이류" -> {
            painterResource(id = R.drawable.ig_grilled)
        }
        "튀김/전" -> {
            painterResource(id = R.drawable.ig_fried)
        }
        "치즈" -> {
            painterResource(id = R.drawable.ig_cheese)
        }
        "샐러드" -> {
            painterResource(id = R.drawable.ig_salad)
        }
        "과일" -> {
            painterResource(id = R.drawable.ig_fruits)
        }
        "국물/스프" -> {
            painterResource(id = R.drawable.ig_soup)
        }
        "디저트" -> {
            painterResource(id = R.drawable.ig_desert)
        }
        "면류" -> {
            painterResource(id = R.drawable.ig_noodle)
        }
        "회/초밥" -> {
            painterResource(id = R.drawable.ig_sushi)
        }

        else -> { painterResource(id = R.drawable.ic_menu_category) }
    }
}
