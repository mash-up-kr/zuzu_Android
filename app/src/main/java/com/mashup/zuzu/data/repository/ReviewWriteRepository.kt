package com.mashup.zuzu.data.repository

import com.mashup.zuzu.data.request.ReviewWriteRequest
import com.mashup.zuzu.data.source.remote.review.ReviewWriteRemoteDataSource

class ReviewWriteRepository constructor(
    private val reviewWriteRemoteDataSource: ReviewWriteRemoteDataSource
) {
    suspend fun finishReviewWrite(wineId: Long, reviewWriteRequest: ReviewWriteRequest): Long {
        return reviewWriteRemoteDataSource.finishReviewWrite(
            wineId = wineId,
            reviewWriteRequest = reviewWriteRequest
        )
    }
}