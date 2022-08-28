package com.mashup.zuzu.data.repository

import com.mashup.zuzu.data.model.Results
import com.mashup.zuzu.data.model.Wine
import com.mashup.zuzu.data.response.GetDrinksEvaluationResponse
import com.mashup.zuzu.data.response.GetReviewWithIdResponse
import com.mashup.zuzu.data.source.remote.review.ReviewDetailRemoteDataSource
import com.mashup.zuzu.data.source.remote.review.ReviewShareRemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import timber.log.Timber
import javax.inject.Inject

class ReviewShareRepository @Inject constructor(
    private val reviewShareRemoteDataSource: ReviewShareRemoteDataSource
) {
    fun getReviewShareResponse(reviewId: Long): Flow<Results<GetReviewWithIdResponse>> {
        return flow {
            val response = reviewShareRemoteDataSource.getReviewShareResponse(reviewId)
            val body = response.body()

            if (response.isSuccessful && body != null) {
                emit(Results.Success(body))
            }
        }

    }
}
