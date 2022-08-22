package com.mashup.zuzu.data.response.model

import com.google.gson.annotations.SerializedName

/**
 * @Created by 김현국 2022/08/20
 */

data class WorldCup(

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
