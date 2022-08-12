package com.mashup.zuzu.data.source.remote.category

import com.mashup.zuzu.data.response.GetDrinksCategoryResponse
import com.mashup.zuzu.data.response.GetDrinksWithCategoryResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @Created by 김현국 2022/08/12
 */
interface CategoryApi {

    @GET("/drinks-category")
    suspend fun getDrinksCategory(): Response<List<GetDrinksCategoryResponse>>

    // 특정 카테고리에 해당하는 주류 가져오기
    @GET("/drinks/category")
    suspend fun getDrinksWithCategory(
        @Query("name") name: String
    ): Response<List<GetDrinksWithCategoryResponse>>
}