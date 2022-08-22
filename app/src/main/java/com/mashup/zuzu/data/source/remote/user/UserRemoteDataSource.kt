package com.mashup.zuzu.data.source.remote.user

import com.mashup.zuzu.data.request.RequestProfile
import com.mashup.zuzu.data.request.UpdateUsersRequest
import com.mashup.zuzu.data.response.GetReviewsDrinksResponse
import com.mashup.zuzu.data.response.GetUserProfileImagesResponse
import com.mashup.zuzu.data.response.model.User
import com.mashup.zuzu.data.source.remote.review.ReviewApi
import retrofit2.Response
import javax.inject.Inject

/**
 * @Created by 김현국 2022/08/12
 */
class UserRemoteDataSource @Inject constructor(
    private val userApi: UserApi,
    private val reviewApi: ReviewApi
) {

    // 유저 데이터 가져오기
    suspend fun getUserData(): Response<User> {
        return userApi.getUser()
    }

    // 유저 데이터 업데이트
    suspend fun updateUser(
        nickname: String,
        profileId: Long
    ): Response<Unit> {
        return userApi.updateUser(
            updateUsersReq = UpdateUsersRequest(
                nickname = nickname,
                profile = RequestProfile(id = profileId)
            )
        )
    }

    // 유저 탈퇴
    suspend fun deleteUser(): Response<Unit> {
        return userApi.deleteUser()
    }

    // 유저 프로필 이미지 가져오기
    suspend fun getUserProfileImages(): Response<List<GetUserProfileImagesResponse>> {
        return userApi.getUserProfileImages()
    }

    suspend fun getReviewsDrinks(
        drinkId: Long
    ): Response<GetReviewsDrinksResponse> {
        return reviewApi.getReviewsDrinks(
            drinkId = drinkId
        )
    }
}
