package com.mashup.zuzu.data.request

/**
 * @Created by 김현국 2022/08/10
 */
data class UpdateUsersRequest(
    val nickname: String,
    val profile: RequestProfile
)

data class RequestProfile(
    val id: Long
)
