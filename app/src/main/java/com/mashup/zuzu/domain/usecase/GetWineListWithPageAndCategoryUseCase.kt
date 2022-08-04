package com.mashup.zuzu.domain.usecase

import androidx.paging.PagingData
import com.mashup.zuzu.data.model.Results
import com.mashup.zuzu.data.model.Wine
import com.mashup.zuzu.data.repository.CategoryRepository
import kotlinx.coroutines.flow.Flow

/**
 * @Created by 김현국 2022/08/03
 */
class GetWineListWithPageAndCategoryUseCase(
    private val repository: CategoryRepository
) {
    operator fun invoke(category: String): Flow<PagingData<Wine>> {
        return repository.getWineListWithPageAndCategory(category = category)
    }
}
