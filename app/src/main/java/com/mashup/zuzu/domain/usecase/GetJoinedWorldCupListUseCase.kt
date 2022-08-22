package com.mashup.zuzu.domain.usecase

import com.mashup.zuzu.ui.model.BestWorldCup
import com.mashup.zuzu.data.model.Results
import com.mashup.zuzu.data.repository.WorldCupRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @Created by 김현국 2022/07/24
 */
@Singleton
class GetJoinedWorldCupListUseCase @Inject constructor(
    private val repository: WorldCupRepository
) {
    operator fun invoke(userId: Long): Flow<Results<List<BestWorldCup>>> {
        return repository.getJoinedWorldCupList(userId = userId)
    }
}
