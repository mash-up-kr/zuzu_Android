package com.mashup.zuzu.data.mapper

import com.mashup.zuzu.data.model.UserProfileImagesModel
import com.mashup.zuzu.data.response.GetUserProfileImagesResponse

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
