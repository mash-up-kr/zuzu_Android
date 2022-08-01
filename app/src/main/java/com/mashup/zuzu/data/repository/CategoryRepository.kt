package com.mashup.zuzu.data.repository

import com.mashup.zuzu.data.model.*
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
    fun getCategoryList(): Flow<Results<List<Category>>> {
        return flow {
            emit(Results.Loading)
            delay(300)
            emit(Results.Success(categoryList))
        }
    }
}
