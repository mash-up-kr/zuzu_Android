package com.mashup.zuzu.data.source.remote.category

import com.mashup.zuzu.data.model.Category
import com.mashup.zuzu.data.model.Results
import com.mashup.zuzu.data.model.Wine
import kotlinx.coroutines.flow.Flow

/**
 * @Created by 김현국 2022/08/08
 */
interface CategoryRemoteDataSource {

    // 주류 카테고리 가져오기
    fun getDrinksCategory(): Flow<Results<List<Category>>>

    // 특정 카테고리에 해당하는 주류 가져오기
    fun getDrinksWithCategory(
        category: String,
        page: Int
    ): Flow<Results<List<Wine>>>
}
