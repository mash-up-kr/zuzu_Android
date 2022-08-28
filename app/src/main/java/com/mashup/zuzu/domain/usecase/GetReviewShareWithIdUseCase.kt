package com.mashup.zuzu.domain.usecase

import com.mashup.zuzu.data.model.Results
import com.mashup.zuzu.data.model.User
import com.mashup.zuzu.data.repository.ReviewShareRepository
import com.mashup.zuzu.data.response.GetReviewWithIdResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetReviewShareWithIdUseCase @Inject constructor(
    private val repository: ReviewShareRepository
) {
    operator fun invoke(reviewId: Long): Flow<Results<GetReviewWithIdResponse>> {
        return repository.getReviewShareResponse(reviewId)
    }
}