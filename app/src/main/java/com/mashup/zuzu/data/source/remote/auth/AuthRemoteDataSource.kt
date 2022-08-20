package com.mashup.zuzu.data.source.remote.auth

import com.mashup.zuzu.data.request.ProofAuthRequest
import com.mashup.zuzu.data.response.GetKakaoAccessTokenResponse
import com.mashup.zuzu.data.response.GetProofAuthResponse
import retrofit2.Response
import javax.inject.Inject

class AuthRemoteDataSource @Inject constructor(
    private val authApi: AuthApi
) {
    suspend fun getProofAuth(kakaoAccessToken: String): Response<GetProofAuthResponse> {
        return authApi.getProofAuth(ProofAuthRequest(kakaoAccessToken))
    }

    suspend fun getKakaoAccessToken(refreshToken: String): Response<GetKakaoAccessTokenResponse> {
        return authApi.getKakaoAccessToken(refreshToken)
    }

    suspend fun logoutProof() {
        return authApi.logoutProof()
    }
}