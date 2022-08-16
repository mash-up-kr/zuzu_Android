package com.mashup.zuzu.data.repository

import com.mashup.zuzu.data.model.Wine
import com.mashup.zuzu.ui.review.ReviewDetailUiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ReviewDetailRepository {
    fun getWineStream(wineId: Long): Flow<Wine> {
        return flow {
            emit(
                Wine(
                    id = 1L,
                    name = "GoldenBlueGoldenBlueGoldenBlueGoldenBlueGoldenBlueGoldenBlueGoldenBlueGoldenBlueGoldenBlueGoldenBlueGoldenBlueGoldenBlueGoldenBlueGoldenBlue",
                    imageUrl = "https://images.absolutdrinks.com/ingredient-images/Raw/Absolut/65d43459-c926-4b12-a15b-afa7a71c2071.jpg?imwidth=500",
                    alc = 17,
                    description = "뜨는 술",
                    category = "와인",
                    tags = listOf("뜨는 술", "맛있는 술"),
                )
            )
        }
    }
}