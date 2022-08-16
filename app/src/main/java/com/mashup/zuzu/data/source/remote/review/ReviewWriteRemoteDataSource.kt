package com.mashup.zuzu.data.source.remote.review

import com.mashup.zuzu.data.di.IoDispatcher
import com.mashup.zuzu.data.request.ReviewWriteRequest
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ReviewWriteRemoteDataSource @Inject constructor(
    private val reviewApi: ReviewApi,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) {
    suspend fun finishReviewWrite(wineId: Long, reviewWriteRequest: ReviewWriteRequest): Long =
        withContext(ioDispatcher) {
            return@withContext reviewApi.finishReviewWrite(
                wineId = wineId,
                reviewWriteRequest = reviewWriteRequest
            )
        }
}