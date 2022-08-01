package com.mashup.zuzu.domain.usecase

import com.mashup.zuzu.data.model.Results
import com.mashup.zuzu.data.model.User
import com.mashup.zuzu.data.repository.UserRepository
import kotlinx.coroutines.flow.Flow

/**
 * @Created by 김현국 2022/07/24
 */
class GetUserDataUseCase(
    private val repository: UserRepository
) {
    operator fun invoke(userId: Long): Flow<Results<User>> {
        return repository.getUserData(userId = userId)
    }
}
