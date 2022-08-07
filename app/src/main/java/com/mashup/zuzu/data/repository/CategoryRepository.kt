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

    fun getWineListWithPageAndCategory(category: String, page: Int): Flow<Results<List<Wine>>> {
        return flow {
            emit(Results.Loading)
            emit(
                Results.Success(
                    PageWineRepo.getWineListWithPage(pageNumber = page, category = category).wines
                )
            )
        }
    }

    fun getCategoryList(): Flow<Results<List<Category>>> {
        return flow {
            emit(Results.Loading)
            delay(1000)
            emit(Results.Success(categoryList))
        }
    }
}
