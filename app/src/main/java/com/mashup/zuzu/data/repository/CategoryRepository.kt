package com.mashup.zuzu.data.repository

import com.mashup.zuzu.data.mapper.categoryResponseToModel
import com.mashup.zuzu.data.model.*
import com.mashup.zuzu.data.source.remote.category.CategoryApi
import com.mashup.zuzu.data.source.remote.category.CategoryRemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

/**
 * @Created by 김현국 2022/07/24
 */
class CategoryRepository @Inject constructor(
    private val categoryApi: CategoryApi
) : CategoryRemoteDataSource {
    override fun getDrinksCategory(): Flow<Results<List<Category>>> {
        return flow {
            emit(Results.Loading)
            val response = categoryApi.getDrinksCategory()
            val body = response.body()
            if (response.isSuccessful && body != null) {
                val data = categoryResponseToModel(categoryResponse = body)
                emit(Results.Success(data))
            }
        }.flowOn(Dispatchers.IO)
    }

    override fun getDrinksWithCategory(category: String, page: Int): Flow<Results<List<Wine>>> {
        return flow {
            emit(Results.Loading)
            emit(
                Results.Success(
                    PageWineRepo.getWineListWithPage(pageNumber = page, category = category).wines
                )
            )
        }.flowOn(Dispatchers.IO)
    }
}
