package com.mashup.zuzu.data.source.remote.category

import com.mashup.zuzu.data.response.GetDrinksCategoryResponse
import retrofit2.Response
import javax.inject.Inject

/**
 * @Created by 김현국 2022/08/08
 */

class CategoryRemoteDataSource @Inject constructor(
    private val categoryApi: CategoryApi
) {

    // 주류 카테고리 가져오기
    suspend fun getDrinksCategory(): Response<List<GetDrinksCategoryResponse>> {
        return categoryApi.getDrinksCategory()
    }
}
