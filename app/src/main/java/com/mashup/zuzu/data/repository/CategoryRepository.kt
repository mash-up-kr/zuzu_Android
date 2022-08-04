package com.mashup.zuzu.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.mashup.zuzu.data.model.*
import com.mashup.zuzu.data.source.pager.WineListPagingSource
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * @Created by 김현국 2022/07/24
 */
class CategoryRepository @Inject constructor() {

    // 카테고리 전체 클릭시
    fun getWineList(): Flow<Results<List<Wine>>> {
        return flow {
            emit(Results.Loading)
            delay(500)
            emit(Results.Success(wines))
        }
    }
    fun getWineListWithCategory(category: String): Flow<Results<List<Wine>>> {
        return flow {
            emit(Results.Loading)
            delay(500)
            emit(Results.Success(wines.filter { it.category == category }))
        }
    }

    fun getWineListWithPageAndCategory(category: String): Flow<PagingData<Wine>> {
        return Pager(
            config = PagingConfig(
                pageSize = 8, // networkPageSize,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                WineListPagingSource(category = category)
            }
        ).flow
    }

    fun getCategoryList(): Flow<Results<List<Category>>> {
        return flow {
            emit(Results.Loading)
            delay(300)
            emit(Results.Success(categoryList))
        }
    }
}
