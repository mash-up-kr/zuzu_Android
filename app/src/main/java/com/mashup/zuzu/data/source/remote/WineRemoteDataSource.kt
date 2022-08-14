package com.mashup.zuzu.data.source.remote

import com.mashup.zuzu.data.response.GetDrinksResponse
import com.mashup.zuzu.data.response.GetDrinksWithIdResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * @Created by 김현국 2022/08/08
 */
interface WineRemoteDataSource {

    // 전체 주류 가져오기
    @GET("/drinks")
    suspend fun getDrinks(): Response<List<GetDrinksResponse>>

    // 특정 id에 해당하는 주류 디테일 정보 가져오기
    @GET("/drinks/{id}")
    suspend fun getGetDrinksWithId(@Path("id") id: Int):
        Response<GetDrinksWithIdResponse>
}
