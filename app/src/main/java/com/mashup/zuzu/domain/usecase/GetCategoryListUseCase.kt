package com.mashup.zuzu.domain.usecase

import com.mashup.zuzu.data.model.Category
import com.mashup.zuzu.data.model.Results
import com.mashup.zuzu.data.repository.CategoryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @Created by 김현국 2022/07/27
 */
@Singleton
class GetCategoryListUseCase @Inject constructor(
    private val repository: CategoryRepository
) {
    operator fun invoke(): Flow<Results<List<Category>>> {
        return repository.getDrinksCategory()
    }
}
