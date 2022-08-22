package com.mashup.zuzu.data.repository

import com.mashup.zuzu.data.mapper.categoryResponseToModel
import com.mashup.zuzu.data.model.*
import com.mashup.zuzu.data.source.remote.category.CategoryRemoteDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

/**
 * @Created by 김현국 2022/07/24
 */
class CategoryRepository constructor(
    private val categoryRemoteDataSource: CategoryRemoteDataSource,
    private val ioDispatcher: CoroutineDispatcher
) {
    fun getDrinksCategory(): Flow<Results<List<Category>>> {
        return flow {
            emit(Results.Loading)
            val response = categoryRemoteDataSource.getDrinksCategory()
            val body = response.body()
            if (response.isSuccessful && body != null) {
                val data = categoryResponseToModel(categoryResponse = body)
                emit(Results.Success(data))
            }
        }.flowOn(ioDispatcher)
    }
}
