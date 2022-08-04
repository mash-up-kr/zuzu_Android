package com.mashup.zuzu.domain.usecase

import com.mashup.zuzu.data.model.Results
import com.mashup.zuzu.data.model.Wine
import com.mashup.zuzu.data.repository.HomeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * @Created by 김현국 2022/07/24
 */
class GetMainWineListUseCase @Inject constructor(
    private val repository: HomeRepository
) {
    operator fun invoke(): Flow<Results<List<Wine>>> {
        return repository.getMainWineList()
    }
}
