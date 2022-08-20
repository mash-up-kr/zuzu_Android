package com.mashup.zuzu.data.repository

import com.mashup.zuzu.data.model.BestWorldCup
import com.mashup.zuzu.data.model.Results
import com.mashup.zuzu.data.model.bestWorldCupList
import com.mashup.zuzu.data.source.remote.worldcup.WorldCupRemoteDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

/**
 * @Created by 김현국 2022/08/13
 */
class WorldCupRepository constructor(
    private val worldCupRemoteDataSource: WorldCupRemoteDataSource,
    private val ioDispatcher: CoroutineDispatcher
) {

    fun getBestWorldCupList(): Flow<Results<List<BestWorldCup>>> {
        return flow {
            emit(Results.Loading)
            delay(500)
            emit(Results.Success(bestWorldCupList.filterIndexed { index, bestWorldCup -> index < 3 }))
        }.flowOn(ioDispatcher)
    }

    fun getJoinedWorldCupList(userId: Long): Flow<Results<List<BestWorldCup>>> {
        return flow {
            emit(Results.Loading)
            delay(1000)
            emit(Results.Success(bestWorldCupList))
        }.flowOn(ioDispatcher)
    }
}
