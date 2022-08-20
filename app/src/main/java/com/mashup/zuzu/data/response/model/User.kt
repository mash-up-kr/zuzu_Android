package com.mashup.zuzu.data.response.model

import com.google.gson.annotations.SerializedName

/**
 * @Created by 김현국 2022/08/11
 */
data class User(

    @SerializedName("accessToken")
    val accessToken: String?,

    @SerializedName("refreshToken")
    val refreshToken: String?,

    @SerializedName("email")
    val email: String,

    @SerializedName("id")
    val id: Long,

    @SerializedName("name")
    val name: String,

    @SerializedName("nickname")
    val nickname: String,

    @SerializedName("profile")
    val profile: Profile,

    @SerializedName("social_id")
    val social_id: String,

    @SerializedName("type")
    val type: String
)
