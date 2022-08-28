package com.mashup.zuzu.data.source.remote.review

import com.mashup.zuzu.data.request.ReviewWriteRequest
import com.mashup.zuzu.data.response.GetDrinksEvaluationResponse
import com.mashup.zuzu.data.response.GetReviewWithIdResponse
import com.mashup.zuzu.data.response.GetReviewsDrinksResponse
import retrofit2.Response
import retrofit2.http.*

interface ReviewApi {
    @POST("/reviews/drinks/{drinkId}")
    suspend fun finishReviewWrite(@Path("drinkId") wineId: Long, @Body reviewWriteRequest: ReviewWriteRequest): Long

    @GET("/reviews/{reviewCardId}")
    suspend fun getReviewShareCard(@Path("reviewCardId") reviewId: Long) : Response<GetReviewWithIdResponse>

    // 술 리뷰 목록 가져오기
    @GET("/reviews/drinks")
    suspend fun getReviewsDrinks(
        @Query("drinkId") drinkId: Long
    ): Response<GetReviewsDrinksResponse>

    @GET("/drinks-evaluation/{drinkId}")
    suspend fun getDrinksEvaluationWithId(@Path("drinkId") drinkId: Long): Response<GetDrinksEvaluationResponse>
}
