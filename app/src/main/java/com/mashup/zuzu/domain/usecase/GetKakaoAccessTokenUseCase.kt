package com.mashup.zuzu.domain.usecase

import com.mashup.zuzu.data.model.Results
import com.mashup.zuzu.data.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetKakaoAccessTokenUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    operator fun invoke(refreshToken: String): Flow<Results<String>> {
        return repository.getKakaoAccessToken(refreshToken = refreshToken)
    }
}