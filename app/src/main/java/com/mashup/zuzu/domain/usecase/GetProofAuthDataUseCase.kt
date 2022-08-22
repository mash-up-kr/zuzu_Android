package com.mashup.zuzu.domain.usecase

import com.mashup.zuzu.data.model.ProofAuth
import com.mashup.zuzu.data.model.Results
import com.mashup.zuzu.data.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetProofAuthDataUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    operator fun invoke(kakaoAccessToken: String): Flow<Results<ProofAuth>> {
        return repository.getProofAuthData(kakaoAccessToken = kakaoAccessToken)
    }
}