package com.mashup.zuzu.data.source.remote.review

import com.mashup.zuzu.data.request.ReviewWriteRequest
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ReviewApi {
    @POST("/reviews/drinks/{drinkId}")
    suspend fun finishReviewWrite(@Path("drinkId") wineId: Long, @Body reviewWriteRequest: ReviewWriteRequest): Long

    @GET()
    suspend fun getReviewShareCard(reviewId: Long)
}