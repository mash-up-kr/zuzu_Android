package com.mashup.zuzu.domain.usecase

import com.mashup.zuzu.data.model.Results
import com.mashup.zuzu.data.model.UserProfileImages
import com.mashup.zuzu.data.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @Created by 김현국 2022/08/16
 */
@Singleton
class GetUserProfileImagesUseCase @Inject constructor(
    private val repository: UserRepository
) {
    operator fun invoke(): Flow<Results<List<UserProfileImages>>> {
        return repository.getUserProfileImages()
    }
}
