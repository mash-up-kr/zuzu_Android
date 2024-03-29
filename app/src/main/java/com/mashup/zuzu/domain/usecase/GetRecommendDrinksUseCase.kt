package com.mashup.zuzu.domain.usecase

import com.mashup.zuzu.data.model.Results
import com.mashup.zuzu.data.model.Wine
import com.mashup.zuzu.data.repository.WineRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @Created by 김현국 2022/07/24
 */
@Singleton
class GetRecommendDrinksUseCase @Inject constructor(
    private val repository: WineRepository
) {
    operator fun invoke(): Flow<Results<List<Wine>>> {
        return repository.getRecommendDrinks()
    }
}
