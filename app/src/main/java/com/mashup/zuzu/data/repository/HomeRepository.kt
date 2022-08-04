package com.mashup.zuzu.data.repository

import com.mashup.zuzu.data.model.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * @Created by 김현국 2022/07/24
 */
class HomeRepository @Inject constructor() {
    fun getMainWineList(): Flow<Results<List<Wine>>> {
        return flow {
            emit(Results.Loading)
            delay(500)
            emit(Results.Success(wines))
        }
    }

    fun getBestWorldCupList(): Flow<Results<List<BestWorldCup>>> {
        return flow {
            emit(Results.Loading)
            delay(500)
            emit(Results.Success(bestWorldCupList.filterIndexed { index, bestWorldCup -> index < 3 }))
        }
    }

    fun getRecommendWine(): Flow<Results<Wine>> {
        return flow {
            emit(Results.Loading)
            delay(500)
            emit(Results.Success(wines[0]))
        }
    }
}
