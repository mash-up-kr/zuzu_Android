package com.mashup.zuzu.domain.usecase

import com.mashup.zuzu.data.model.Results
import com.mashup.zuzu.data.model.User
import com.mashup.zuzu.data.repository.UserRepository
import kotlinx.coroutines.flow.Flow

/**
 * @Created by 김현국 2022/07/24
 * @Time 4:22 오후
 * @Description 아마 api로 따로 부르지 않고, 이미 인증 처리 된 부분에서 가져오게 될 듯 임시.
 */
class GetUserDataUseCase(
    private val repository: UserRepository
) {
    operator fun invoke(userId: Long): Flow<Results<User>> {
        return repository.getUserData(userId = userId)
    }
}
