package com.mashup.zuzu.data.source.remote.auth

import com.mashup.zuzu.data.request.KakaoAccessTokenRequest
import com.mashup.zuzu.data.request.ProofAuthRequest
import com.mashup.zuzu.data.response.GetKakaoAccessTokenResponse
import com.mashup.zuzu.data.response.GetProofAuthResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {

    // 카카오 로그인
    @POST("/auth/kakao-login")
    suspend fun getProofAuth(@Body proofAuthRequest: ProofAuthRequest): Response<GetProofAuthResponse>

    // 로그인 토큰 갱신
    @POST("/auth/token-refresh")
    suspend fun getKakaoAccessToken(@Body kakaoAccessTokenRequest: KakaoAccessTokenRequest): Response<GetKakaoAccessTokenResponse>

    // 사용자 로그아웃
    @POST("/auth/logout")
    suspend fun logoutProof()
}