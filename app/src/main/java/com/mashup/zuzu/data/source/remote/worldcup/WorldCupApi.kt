package com.mashup.zuzu.data.source.remote.worldcup

import com.mashup.zuzu.data.response.GetWorldCupUserParticipatedResponse
import com.mashup.zuzu.data.response.model.WorldCup
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * @Created by 김현국 2022/08/08
 */
interface WorldCupApi {

    @GET("/worldcups/popular")
    suspend fun getPopularWorldCup(): Response<List<WorldCup>>

    @GET("/worldcups/{id}/item")
    suspend fun getWorldCupItemWithId(@Path("id") id: String)

    @GET("/worldcups/user-participated")
    suspend fun getWorldCupUserParticipated(): Response<List<GetWorldCupUserParticipatedResponse>>
}
