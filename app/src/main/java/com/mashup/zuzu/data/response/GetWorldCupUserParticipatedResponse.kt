package com.mashup.zuzu.data.response

import com.google.gson.annotations.SerializedName
import com.mashup.zuzu.data.response.model.WorldCup

/**
 * @Created by 김현국 2022/08/22
 */
data class GetWorldCupUserParticipatedResponse(

    @SerializedName("id")
    val id: Long,

    @SerializedName("winnerDrinkId")
    val winnerDrinkId: Long,

    @SerializedName("worldcup")
    val worldcup: WorldCup

)
