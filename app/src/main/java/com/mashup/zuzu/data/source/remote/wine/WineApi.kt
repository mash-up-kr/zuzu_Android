package com.mashup.zuzu.data.source.remote.wine

import com.mashup.zuzu.data.response.GetDrinksWithCategoryResponse
import com.mashup.zuzu.data.response.model.Wine
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * @Created by 김현국 2022/08/08
 */
interface WineApi {

    // 특정 id에 해당하는 주류 디테일 정보 가져오기
    @GET("/drinks/{id}")
    suspend fun getDrinksWithId(@Path("id") id: Long): Response<Wine>

    // 추천 술 가져오기
    @GET("/drinks/recommend")
    suspend fun getRecommendDrinks(): Response<List<Wine>>

    // 랜덤 술 가져오기
    @GET("/drinks/random")
    suspend fun getRandomDrinks(): Response<Wine>

    // 특정 카테고리에 해당하는 주류 가져오기
    @GET("/drinks/category")
    suspend fun getDrinksWithCategory(
        @Query("name") name: String,
        @Query("page") page: Int,
        @Query("length") length: Int
    ): Response<GetDrinksWithCategoryResponse>

    // 유저페이지 술 저장고 내부 술 리스트 가져오기
    @GET("/drinks/users/reviews")
    suspend fun getDrinksReviewsInUserPage(): Response<List<Wine>>
}
