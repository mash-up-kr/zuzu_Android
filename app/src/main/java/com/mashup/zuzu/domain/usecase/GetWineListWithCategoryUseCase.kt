package com.mashup.zuzu.domain.usecase

import com.mashup.zuzu.data.model.Results
import com.mashup.zuzu.data.model.Wine
import com.mashup.zuzu.data.repository.CategoryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @Created by 김현국 2022/07/24
 */
@Singleton
class GetWineListWithCategoryUseCase @Inject constructor(
    private val repository: CategoryRepository
) {
    operator fun invoke(category: String): Flow<Results<List<Wine>>> {
        return repository.getWineListWithCategory(category = category)
    }
}
