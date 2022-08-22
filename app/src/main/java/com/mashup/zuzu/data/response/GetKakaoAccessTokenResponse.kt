package com.mashup.zuzu.data.response

import com.google.gson.annotations.SerializedName

data class GetKakaoAccessTokenResponse(
    @SerializedName("accessToken")
    val accessToken: String
)