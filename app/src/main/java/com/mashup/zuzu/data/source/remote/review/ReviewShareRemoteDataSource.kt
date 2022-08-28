package com.mashup.zuzu.data.source.remote.review

import com.mashup.zuzu.data.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

class ReviewShareRemoteDataSource @Inject constructor(
    private val reviewApi: ReviewApi,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) {
    suspend fun getReviewShareResponse(reviewId: Long) = withContext(ioDispatcher) {
        return@withContext reviewApi.getReviewShareCard(reviewId)
    }
}