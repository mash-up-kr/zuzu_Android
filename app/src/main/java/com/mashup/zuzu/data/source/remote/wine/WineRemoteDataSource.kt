package com.mashup.zuzu.data.source.remote.wine

import com.mashup.zuzu.data.model.BestWorldCup
import com.mashup.zuzu.data.model.Results
import com.mashup.zuzu.data.model.Wine
import kotlinx.coroutines.flow.Flow

/**
 * @Created by 김현국 2022/08/12
 */
interface WineRemoteDataSource {

    fun getDrinks(): Flow<Results<List<Wine>>>

    fun getDrinksWithId(
        id: Int
    ): Flow<Results<Wine>>

    fun getRecommendWine(): Flow<Results<Wine>>

    fun getBestWorldCupList(): Flow<Results<List<BestWorldCup>>>
}
