package com.mashup.zuzu.data.source.remote.user

import com.mashup.zuzu.data.model.*
import com.mashup.zuzu.data.request.UpdateUsersRequest
import com.mashup.zuzu.data.response.GetUserProfileImagesResponse
import com.mashup.zuzu.data.response.GetUsersResponse
import retrofit2.Response
import javax.inject.Inject

/**
 * @Created by 김현국 2022/08/12
 */
class UserRemoteDataSource @Inject constructor(
    private val userApi: UserApi
) {

    // 유저 데이터 가져오기
    suspend fun getUserData(userId: Long): Response<GetUsersResponse> {
        return userApi.getUser()
    }

    // 유저 데이터 업데이트
    suspend fun updateUser(
        nickname: String,
        profileId: Int
    ): Response<Nothing> {
        return userApi.updateUser(
            updateUsersReq = UpdateUsersRequest(
                nickname = nickname,
                profile_id = profileId
            )
        )
    }

    // 유저 탈퇴
    suspend fun deleteUser(): Response<Nothing> {
        return userApi.deleteUser()
    }

    // 유저 프로필 이미지 가져오기
    suspend fun getUserProfileImages(): Response<List<GetUserProfileImagesResponse>> {
        return userApi.getUserProfileImages()
    }
}
