package com.mashup.zuzu.domain.usecase

import com.mashup.zuzu.data.model.Results
import com.mashup.zuzu.data.model.Wine
import com.mashup.zuzu.data.repository.HomeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * @Created by 김현국 2022/07/24
 * @Time 3:42 오후
 * @Description Home Screen에서 추천 와인 이미지
 */
class GetRecommendWineUseCase @Inject constructor(
    private val repository: HomeRepository
) {
    operator fun invoke(): Flow<Results<Wine>> {
        return repository.getRecommendWine()
    }
}
