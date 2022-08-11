package com.mashup.zuzu.data.response

import com.google.gson.annotations.SerializedName
import com.mashup.zuzu.data.response.model.User

data class GetUsersResponse(

    @SerializedName("user")
    val user: User
)
