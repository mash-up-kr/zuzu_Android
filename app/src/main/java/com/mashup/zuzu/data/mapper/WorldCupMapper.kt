package com.mashup.zuzu.data.mapper

import com.mashup.zuzu.data.response.GetWorldCupPopularResponse
import com.mashup.zuzu.ui.model.BestWorldCup

/**
 * @Created by 김현국 2022/08/21
 */

fun worldCupResponseToUiModel(
    getWorldCupPopularResponse: List<GetWorldCupPopularResponse>
): List<BestWorldCup> {
    return getWorldCupPopularResponse.map { it ->
        BestWorldCup(
            title = it.title,
            participants = it.participantCount,
            image = ""
        )
    }
}
