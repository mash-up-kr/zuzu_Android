package com.mashup.zuzu.domain.usecase

import com.mashup.zuzu.data.model.Results
import com.mashup.zuzu.data.repository.WorldCupRepository
import com.mashup.zuzu.data.response.model.WorldCup
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @Created by 김현국 2022/07/24
 */

@Singleton
class GetBestWorldCupListUseCase @Inject constructor(
    private val repository: WorldCupRepository
) {
    operator fun invoke(): Flow<Results<List<WorldCup>>> {
        return repository.getBestWorldCupList()
    }
}
