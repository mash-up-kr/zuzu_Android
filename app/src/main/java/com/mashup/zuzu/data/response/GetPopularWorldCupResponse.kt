package com.mashup.zuzu.data.response

import com.google.gson.annotations.SerializedName

/**
 * @Created by 김현국 2022/08/20
 */

data class GetWorldCupPopularResponse(

    @SerializedName("id")
    val id: Int,

    @SerializedName("title")
    val title: String,

    @SerializedName("imageUrl")
    val imageUrl: String,

    @SerializedName("withWho")
    val withWho: WithWho,

    @SerializedName("situation")
    val situation: Situation,

    @SerializedName("round")
    val round: List<Round>,

    @SerializedName("participantCount")
    val participantCount: Int
)

data class WithWho(

    @SerializedName("code")
    val code: String,

    @SerializedName("content")
    val content: String,

    @SerializedName("title")
    val title: String
)

data class Situation(

    @SerializedName("code")
    val code: String,

    @SerializedName("content")
    val content: String,

    @SerializedName("title")
    val title: String
)

data class Round(

    @SerializedName("count")
    val count: Int,

    @SerializedName("title")
    val title: String
)
