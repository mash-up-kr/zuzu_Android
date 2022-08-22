package com.mashup.zuzu.data.repository

import com.mashup.zuzu.data.mapper.*
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

    // 요즘 사람들은 무슨 술을 마실까?
    fun getRecommendDrinks(): Flow<Results<List<Wine>>> {
        return flow {
            emit(Results.Loading)
            val response = wineRemoteDataSource.getRecommendDrinks()
            val body = response.body()
            if (response.isSuccessful && body != null) {
                val data = responseWineListModelToListDataModel(wineList = body)
                emit(Results.Success(data))
            }
        }.flowOn(ioDispatcher)
    }

    // 특정 카테고리에 해당하는 주류 가져오기
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

    // 랜덤 술 가져오기
    fun getRandomDrinks(): Flow<Results<Wine>> {
        return flow {
            emit(Results.Loading)
            delay(500)
            val response = wineRemoteDataSource.getRandomDrinks()
            val body = response.body()
            if (response.isSuccessful && body != null) {
                val data = responseWineModelToDataModel(body)
                emit(Results.Success(data))
            }
        }.flowOn(ioDispatcher)
    }

    // 특정 id에 해당하는 주류 디테일 정보 가져오기
    fun getDrinksWithId(id: Long): Flow<Results<Wine>> {
        return flow {
            emit(Results.Loading)
            val response = wineRemoteDataSource.getDrinksWithId(id = id)
            val body = response.body()
            if (response.isSuccessful && body != null) {
                val data = responseWineModelToDataModel(body)
                emit(Results.Success(data))
            }
        }.flowOn(ioDispatcher)
    }

    // 유저페이지 술 저장고 내부 술 리스트 가져오기
    fun getDrinksReviewsInUserPage(): Flow<Results<List<Wine>>> {
        return flow {
            emit(Results.Loading)
            val response = wineRemoteDataSource.getDrinksReviewsInUserPage()
            val body = response.body()
            if (response.isSuccessful && body != null) {
                val data = responseWineListModelToListDataModel(wineList = body)
                emit(Results.Success(data))
            }
            emit(Results.Success(wines))
        }.flowOn(ioDispatcher)
    }
}
