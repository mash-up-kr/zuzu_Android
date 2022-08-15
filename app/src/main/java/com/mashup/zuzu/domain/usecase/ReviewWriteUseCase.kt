package com.mashup.zuzu.domain.usecase

import com.mashup.zuzu.data.repository.ReviewWriteRepository
import com.mashup.zuzu.data.request.ReviewWriteRequest
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ReviewWriteUseCase @Inject constructor(
    private val reviewWriteRepository: ReviewWriteRepository
) {
    suspend operator fun invoke(reviewWriteRequest: ReviewWriteRequest) {
        reviewWriteRepository.finishReviewWrite(reviewWriteRequest)
    }
}