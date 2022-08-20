package com.mashup.zuzu.data.repository

import com.mashup.zuzu.data.mapper.nameToEnglish
import com.mashup.zuzu.data.mapper.wineListResponseToModel
import com.mashup.zuzu.data.mapper.wineResponseToModel
import com.mashup.zuzu.data.mapper.wineWithCategoryResponseToModel
import com.mashup.zuzu.data.model.*
import com.mashup.zuzu.data.source.remote.wine.WineRemoteDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

/**
 * @Created by 김현국 2022/07/24
 */
class WineRepository constructor(
    private val wineRemoteDataSource: WineRemoteDataSource,
    private val ioDispatcher: CoroutineDispatcher
) {
    fun getDrinks(): Flow<Results<List<Wine>>> {
        return flow {
            emit(Results.Loading)
            val response = wineRemoteDataSource.getDrinks()
            val body = response.body()
            if (response.isSuccessful && body != null) {
                val data = wineListResponseToModel(getDrinksResponse = body)
                emit(Results.Success(data))
            }
        }.flowOn(ioDispatcher)
    }

    fun getDrinksWithCategory(category: String, page: Int): Flow<Results<WineWithCategoryModel>> {
        return flow {
            emit(Results.Loading)
            val response = wineRemoteDataSource.getDrinksWithCategory(
                name = nameToEnglish(category),
                page = page,
                length = 8
            )
            val body = response.body()
            if (response.isSuccessful && body != null) {
                val data = wineWithCategoryResponseToModel(body)
                emit(Results.Success(data))
            }
        }.flowOn(ioDispatcher)
    }

    fun getRecommendWine(): Flow<Results<Wine>> {
        return flow {
            emit(Results.Loading)
            delay(500)
            emit(Results.Success(wines[0]))
        }.flowOn(ioDispatcher)
    }

    fun getDrinksWithId(id: Long): Flow<Results<Wine>> {
        return flow {
            emit(Results.Loading)
            val response = wineRemoteDataSource.getDrinksWithId(id = id)
            val body = response.body()
            if (response.isSuccessful && body != null) {
                val data = wineResponseToModel(body)
                emit(Results.Success(data))
            }
        }.flowOn(ioDispatcher)
    }

    fun getDrinksReviewsInUserPage(): Flow<Results<List<Wine>>> {
        return flow {
            emit(Results.Loading)
            val response = wineRemoteDataSource.getDrinksReviewsInUserPage()
            val body = response.body()
            if (response.isSuccessful && body != null) {
                val data = wineListResponseToModel(getDrinksResponse = body)
                emit(Results.Success(data))
            }
            emit(Results.Success(wines))
        }.flowOn(ioDispatcher)
    }
}
