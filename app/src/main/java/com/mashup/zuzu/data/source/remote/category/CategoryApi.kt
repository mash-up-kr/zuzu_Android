package com.mashup.zuzu.data.source.remote.category

import com.mashup.zuzu.data.response.GetDrinksCategoryResponse
import retrofit2.Response
import retrofit2.http.GET

/**
 * @Created by 김현국 2022/08/12
 */
interface CategoryApi {

    @GET("/drinks-category")
    suspend fun getDrinksCategory(): Response<List<GetDrinksCategoryResponse>>
}
