package com.mashup.zuzu.data.source.remote.review

import com.mashup.zuzu.data.request.ReviewWriteRequest
import retrofit2.http.GET
import retrofit2.http.POST

interface ReviewApi {
    @POST
    suspend fun finishReviewWrite(reviewWriteRequest: ReviewWriteRequest): Long

    @GET
    suspend fun getReviewShareCard(reviewId: Long)
}