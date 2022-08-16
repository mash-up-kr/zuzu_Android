package com.mashup.zuzu.data.mapper

import com.mashup.zuzu.data.model.User
import com.mashup.zuzu.data.model.UserProfileImagesModel
import com.mashup.zuzu.data.response.GetUserProfileImagesResponse
import com.mashup.zuzu.data.response.GetUsersResponse

/**
 * @Created by 김현국 2022/08/13
 */

fun userProfileImagesResponseToModel(
    getUserProfileImagesResponse: List<GetUserProfileImagesResponse>
): List<UserProfileImagesModel> {
    return getUserProfileImagesResponse.map { response ->
        UserProfileImagesModel(
            id = response.id,
            image_url = response.image
        )
    }
}

fun userResponseToModel(
    getUsersResponse: GetUsersResponse
): User {
    return User(
        id = getUsersResponse.user.id,
        name = getUsersResponse.user.nickname,
        email = getUsersResponse.user.social_id,
        image = getUsersResponse.user.profile.image_url
    )
}
