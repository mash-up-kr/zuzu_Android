package com.mashup.zuzu.domain.usecase

import com.mashup.zuzu.data.model.Results
import com.mashup.zuzu.data.model.Wine
import com.mashup.zuzu.data.repository.HomeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * @Created by 김현국 2022/07/24
 * @Time 3:39 오후컵
 * @Description Home Screen 최상단 요즘 사람들은 무엇을 마실까 ?
 */
class GetMainWineListUseCase @Inject constructor(
    private val repository: HomeRepository
) {
    operator fun invoke(): Flow<Results<List<Wine>>> {
        return repository.getMainWineList()
    }
}
