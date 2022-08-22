package com.mashup.zuzu.data.mapper

import com.mashup.zuzu.data.model.User
import com.mashup.zuzu.data.model.UserProfileImages
import com.mashup.zuzu.data.response.GetUserProfileImagesResponse
import com.mashup.zuzu.data.response.model.User as UserResponse

/**
 * @Created by 김현국 2022/08/13
 */

fun userProfileImagesResponseToModel(
    getUserProfileImagesResponse: List<GetUserProfileImagesResponse>
): List<UserProfileImages> {
    return getUserProfileImagesResponse.map { response ->
        UserProfileImages(
            id = response.id,
            image_url = response.image
        )
    }
}

fun userResponseToModel(
    user: UserResponse
): User {
    return User(
        id = user.id,
        name = user.nickname,
        email = user.email,
        image = user.profile.image_url
    )
}
