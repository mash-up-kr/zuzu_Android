package com.mashup.zuzu.data.repository

import com.mashup.zuzu.data.mapper.proofAuthResponseToModel
import com.mashup.zuzu.data.model.ProofAuth
import com.mashup.zuzu.data.model.Results
import com.mashup.zuzu.data.source.remote.auth.AuthRemoteDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val authRemoteDataSource: AuthRemoteDataSource,
    private val ioDispatcher: CoroutineDispatcher
) {
    fun getProofAuthData(kakaoAccessToken: String): Flow<Results<ProofAuth>> {
        return flow {
            emit(Results.Loading)
            val response = authRemoteDataSource.getProofAuth(kakaoAccessToken)
            val data = response.body()
            if (response.isSuccessful && data != null) {
                emit(Results.Success(proofAuthResponseToModel(data)))
            } else {
                emit(Results.Failure(response.message()))
            }
        }.flowOn(ioDispatcher)
    }

    fun getKakaoAccessTokenData(refreshToken: String): Flow<Results<String>> {
        return flow {
            emit(Results.Loading)
            val response = authRemoteDataSource.getKakaoAccessToken(refreshToken)
            val data = response.body()
            if (response.isSuccessful && data != null) {
                emit(Results.Success(data.accessToken))
            } else {
                emit(Results.Failure(response.message()))
            }
        }.flowOn(ioDispatcher)
    }
}