package com.mashup.zuzu.domain.usecase

import com.mashup.zuzu.data.model.Results
import com.mashup.zuzu.data.repository.UserRepository
import kotlinx.coroutines.flow.Flow

/**
 * @Created by 김현국 2022/07/26
 * @Time 2:40 오후
 */
class LeaveMembershipUseCase(
    private val repository: UserRepository
) {
    operator fun invoke(userId: Long): Flow<Results<String>> {
        return repository.leaveMembership(userId = userId)
    }
}
