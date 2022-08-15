package com.mashup.zuzu.data.repository

import com.mashup.zuzu.data.request.ReviewWriteRequest
import timber.log.Timber

class ReviewWriteRepository constructor(

) {
    suspend fun finishReviewWrite(reviewWriteRequest: ReviewWriteRequest) {
        Timber.tag("Teddy").d("finishReviewWrite : ")
    }
}