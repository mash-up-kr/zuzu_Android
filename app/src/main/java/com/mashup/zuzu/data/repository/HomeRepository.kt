package com.mashup.zuzu.data.repository

import com.mashup.zuzu.data.mapper.wineResponseToModel
import com.mashup.zuzu.data.model.*
import com.mashup.zuzu.data.source.remote.wine.WineApi
import com.mashup.zuzu.data.source.remote.wine.WineRemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

/**
 * @Created by 김현국 2022/07/24
 */
class HomeRepository @Inject constructor(
    private val wineApi: WineApi
) : WineRemoteDataSource {
    override fun getDrinks(): Flow<Results<List<Wine>>> {
        return flow {
            emit(Results.Loading)
            val response = wineApi.getDrinks()
            val body = response.body()
            if (response.isSuccessful && body != null) {
                val data = wineResponseToModel(getDrinksResponse = body)
                emit(Results.Success(data))
            }
        }.flowOn(Dispatchers.IO)
    }

    override fun getBestWorldCupList(): Flow<Results<List<BestWorldCup>>> {
        return flow {
            emit(Results.Loading)
            delay(500)
            emit(Results.Success(bestWorldCupList.filterIndexed { index, bestWorldCup -> index < 3 }))
        }.flowOn(Dispatchers.IO)
    }

    override fun getRecommendWine(): Flow<Results<Wine>> {
        return flow {
            emit(Results.Loading)
            delay(500)
            emit(Results.Success(wines[0]))
        }.flowOn(Dispatchers.IO)
    }

    override fun getDrinksWithId(id: Int): Flow<Results<Wine>> {
        return flow {
            emit(Results.Loading)
        }
    }
}
