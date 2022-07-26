package com.mashup.zuzu.domain.usecase

import com.mashup.zuzu.data.model.BestWorldCup
import com.mashup.zuzu.data.model.Results
import com.mashup.zuzu.data.repository.HomeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * @Created by 김현국 2022/07/24
 * @Time 3:41 오후
 * @Description Home Screen에서 인기 술드컵
 */
class GetBestWorldCupListUseCase @Inject constructor(
    private val repository: HomeRepository
) {
    operator fun invoke(): Flow<Results<List<BestWorldCup>>> {
        return repository.getBestWorldCupList()
    }
}
