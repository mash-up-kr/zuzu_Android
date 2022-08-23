package com.mashup.zuzu.data.repository

import com.mashup.zuzu.data.model.Results
import com.mashup.zuzu.data.model.Wine
import com.mashup.zuzu.data.response.GetDrinksEvaluationResponse
import com.mashup.zuzu.data.source.remote.review.ReviewDetailRemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ReviewDetailRepository @Inject constructor(
    private val reviewDetailRemoteDataSource: ReviewDetailRemoteDataSource
) {
    fun getWineStream(wineId: Long): Flow<Wine> {
        return flow {
            emit(
                Wine(
                    id = 1L,
                    name = "GoldenBlueGoldenBlueGoldenBlueGoldenBlueGoldenBlueGoldenBlueGoldenBlueGoldenBlueGoldenBlueGoldenBlueGoldenBlueGoldenBlueGoldenBlueGoldenBlue",
                    imageUrl = "https://images.absolutdrinks.com/ingredient-images/Raw/Absolut/65d43459-c926-4b12-a15b-afa7a71c2071.jpg?imwidth=500",
                    alc = 17f,
                    description = "뜨는 술",
                    category = "와인",
                    tags = listOf("뜨는 술", "맛있는 술"),
                    worldcupWinCount = null,
                    worldcupSemiFinalCount = null
                )
            )
        }
    }

    fun getDrinksEvaluationWithId(wineId: Long): Flow<Results<GetDrinksEvaluationResponse>> {
        return flow {
            val response = reviewDetailRemoteDataSource.getDrinksEvaluationWithId(wineId = wineId)
            val body = response.body()

            if (response.isSuccessful && body != null) {
                emit(Results.Success(body))
            }
        }
    }
}
