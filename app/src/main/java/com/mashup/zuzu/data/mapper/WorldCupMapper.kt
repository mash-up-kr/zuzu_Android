package com.mashup.zuzu.data.mapper

import com.mashup.zuzu.data.response.GetWorldCupUserParticipatedResponse
import com.mashup.zuzu.data.response.model.WorldCup
import com.mashup.zuzu.ui.model.BestWorldCup

/**
 * @Created by 김현국 2022/08/21
 */

fun worldCupResponseToUiModel(
    getWorldCupPopularResponse: List<WorldCup>
): List<BestWorldCup> {
    return getWorldCupPopularResponse.map { it ->
        BestWorldCup(
            title = it.title,
            participants = it.participantCount,
            image = it.imageUrl
        )
    }
}

fun joinWorldListResponseToUiModel(
    getWorldCupUserParticipatedResponse: List<GetWorldCupUserParticipatedResponse>
): List<BestWorldCup> {
    return getWorldCupUserParticipatedResponse.map {
        BestWorldCup(
            title = it.worldcup.title,
            participants = it.worldcup.participantCount,
            image = it.worldcup.imageUrl
        )
    }
}
