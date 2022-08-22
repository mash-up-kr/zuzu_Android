package com.mashup.zuzu.data.source.remote.wine

import com.mashup.zuzu.data.response.GetDrinksWithCategoryResponse
import com.mashup.zuzu.data.response.model.Wine
import retrofit2.Response
import javax.inject.Inject

/**
 * @Created by 김현국 2022/08/12
 */
class WineRemoteDataSource @Inject constructor(
    private val wineApi: WineApi
) {

    // 요즘 사람들은 무슨 술을 마실까?
    suspend fun getRecommendDrinks(): Response<List<Wine>> {
        return wineApi.getRecommendDrinks()
    }

    // 특정 id에 해당하는 주류 디테일 정보 가져오기
    suspend fun getDrinksWithId(
        id: Long
    ): Response<Wine> {
        return wineApi.getDrinksWithId(id = id)
    }

    // 특정 카테고리에 해당하는 주류 가져오기
    suspend fun getDrinksWithCategory(
        name: String,
        page: Int,
        length: Int
    ): Response<GetDrinksWithCategoryResponse> {
        return wineApi.getDrinksWithCategory(
            name = name,
            page = page,
            length = length
        )
    }

    // 유저페이지 술 저장고 내부 술 리스트 가져오기
    suspend fun getDrinksReviewsInUserPage(): Response<List<Wine>> {
        return wineApi.getDrinksReviewsInUserPage()
    }

    // 랜덤 술 가져오기
    suspend fun getRandomDrinks(): Response<Wine> {
        return wineApi.getRandomDrinks()
    }
}
