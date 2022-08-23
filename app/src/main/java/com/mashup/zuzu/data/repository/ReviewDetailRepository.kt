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
