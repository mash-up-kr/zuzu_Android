package com.mashup.zuzu.data.repository

import com.mashup.zuzu.data.model.Results
import com.mashup.zuzu.data.response.GetWorldCupUserParticipatedResponse
import com.mashup.zuzu.data.response.model.WorldCup
import com.mashup.zuzu.data.source.remote.worldcup.WorldCupRemoteDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

/**
 * @Created by 김현국 2022/08/13
 */
class WorldCupRepository constructor(
    private val worldCupRemoteDataSource: WorldCupRemoteDataSource,
    private val ioDispatcher: CoroutineDispatcher
) {

    fun getBestWorldCupList(): Flow<Results<List<WorldCup>>> {
        return flow {
            emit(Results.Loading)
            val response = worldCupRemoteDataSource.getPopularWorldCup()
            val body = response.body()

            if (response.isSuccessful && body != null) {
                emit(Results.Success(body))
            }
        }.flowOn(ioDispatcher)
    }

    fun getJoinedWorldCupList(): Flow<Results<List<GetWorldCupUserParticipatedResponse>>> {
        return flow {
            emit(Results.Loading)
            val response = worldCupRemoteDataSource.getWorldCupUserParticipated()
            val body = response.body()
            if (response.isSuccessful && body != null) {
                emit(Results.Success(body))
            } else {
                emit(Results.Success(emptyList()))
            }
        }.flowOn(ioDispatcher)
    }
}
