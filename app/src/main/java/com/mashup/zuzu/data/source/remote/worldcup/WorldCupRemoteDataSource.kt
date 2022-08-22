package com.mashup.zuzu.data.source.remote.worldcup

import com.mashup.zuzu.data.response.GetWorldCupUserParticipatedResponse
import com.mashup.zuzu.data.response.model.WorldCup
import retrofit2.Response
import javax.inject.Inject

/**
 * @Created by 김현국 2022/08/13
 */
class WorldCupRemoteDataSource @Inject constructor(
    private val worldCupApi: WorldCupApi
) {

    suspend fun getPopularWorldCup(): Response<List<WorldCup>> {
        return worldCupApi.getPopularWorldCup()
    }

    suspend fun getWorldCupUserParticipated(): Response<List<GetWorldCupUserParticipatedResponse>> {
        return worldCupApi.getWorldCupUserParticipated()
    }
}
