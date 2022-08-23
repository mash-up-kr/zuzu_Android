package com.mashup.zuzu.domain.usecase

import com.mashup.zuzu.data.model.Results
import com.mashup.zuzu.data.repository.ReviewDetailRepository
import com.mashup.zuzu.data.response.GetDrinksEvaluationResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @Created by 김현국 2022/08/23
 */

@Singleton
class GetDrinksEvaluationWithIdUseCase @Inject constructor(
    private val repository: ReviewDetailRepository
) {
    operator fun invoke(wineId: Long): Flow<Results<GetDrinksEvaluationResponse>> {
        return repository.getDrinksEvaluationWithId(wineId = wineId)
    }
}
