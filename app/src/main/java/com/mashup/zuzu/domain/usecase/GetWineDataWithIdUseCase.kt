package com.mashup.zuzu.domain.usecase

import com.mashup.zuzu.data.model.Results
import com.mashup.zuzu.data.model.Wine
import com.mashup.zuzu.data.repository.WineRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @Created by 김현국 2022/08/14
 */
@Singleton
class GetWineDataWithIdUseCase @Inject constructor(
    private val repository: WineRepository
) {
    operator fun invoke(id: Long): Flow<Results<Wine>> {
        return repository.getDrinksWithId(id = id)
    }
}
