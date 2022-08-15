package com.mashup.zuzu.domain.usecase

import com.mashup.zuzu.data.model.Results
import com.mashup.zuzu.data.model.WineWithCategoryModel
import com.mashup.zuzu.data.repository.WineRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @Created by 김현국 2022/08/03
 */
@Singleton
class GetWineListWithPageAndCategoryUseCase @Inject constructor(
    private val repository: WineRepository
) {
    operator fun invoke(category: String, page: Int): Flow<Results<WineWithCategoryModel>> {
        return repository.getDrinksWithCategory(category = category, page = page)
    }
}
