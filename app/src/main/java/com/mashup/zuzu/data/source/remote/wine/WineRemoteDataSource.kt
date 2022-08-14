package com.mashup.zuzu.data.source.remote.wine

import com.mashup.zuzu.data.response.GetDrinksResponse
import com.mashup.zuzu.data.response.GetDrinksWithCategoryResponse
import com.mashup.zuzu.data.response.GetDrinksWithIdResponse
import retrofit2.Response
import timber.log.Timber
import javax.inject.Inject

/**
 * @Created by 김현국 2022/08/12
 */
class WineRemoteDataSource @Inject constructor(
    private val wineApi: WineApi
) {

    suspend fun getDrinks(): Response<List<GetDrinksResponse>> {
        return wineApi.getDrinks()
    }

    suspend fun getDrinksWithId(
        id: Int
    ): Response<GetDrinksWithIdResponse> {
        return wineApi.getDrinksWithId(id = id)
    }

    // 특정 카테고리에 해당하는 주류 가져오기
    suspend fun getDrinksWithCategory(
        name: String,
        page: Int,
        length: Int
    ): Response<GetDrinksWithCategoryResponse> {
        Timber.tag("threadDataSource").d(Thread.currentThread().toString())
        return wineApi.getDrinksWithCategory(
            name = name,
            page = page,
            length = length
        )
    }

    // 유저페이지 술 저장고 내부 술 리스트 가져오기
    suspend fun getDrinksReviewsInUserPage(): Response<List<GetDrinksResponse>> {
        return wineApi.getDrinksReviewsInUserPage()
    }
}
