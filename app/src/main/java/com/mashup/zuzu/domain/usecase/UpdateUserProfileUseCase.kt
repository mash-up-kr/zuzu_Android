package com.mashup.zuzu.domain.usecase

import com.mashup.zuzu.data.model.Results
import com.mashup.zuzu.data.repository.UserRepository
import kotlinx.coroutines.flow.Flow

/**
 * @Created by 김현국 2022/07/24
 */
class UpdateUserProfileUseCase(
    private val repository: UserRepository
) {
    operator fun invoke(profileName: String): Flow<Results<String>> {
        return repository.updateUserProfile()
    }
}
