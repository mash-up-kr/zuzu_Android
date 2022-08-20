package com.mashup.zuzu.domain.usecase

import com.mashup.zuzu.data.model.Results
import com.mashup.zuzu.data.model.ReviewShareCards
import com.mashup.zuzu.data.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @Created by 김현국 2022/08/20
 */

@Singleton
class GetReviewsDrinksUseCase @Inject constructor(
    private val repository: UserRepository
) {
    operator fun invoke(drinkId: Long): Flow<Results<ReviewShareCards>> {
        return repository.getReviewsDrinks(
            drinkId = drinkId
        )
    }
}
