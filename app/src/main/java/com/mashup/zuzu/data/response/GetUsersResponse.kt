package com.mashup.zuzu.data.response

import com.google.gson.annotations.SerializedName

data class GetUsersResponse(

    @SerializedName("user")
    val user: User
)

data class Profile(

    @SerializedName("id")
    val id: Long,

    @SerializedName("image_url")
    val image_url: String
)

data class User(

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
