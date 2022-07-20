package com.mashup.zuzu.data.repository

import com.mashup.zuzu.R
import com.mashup.zuzu.data.model.Wine
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

//TODO: String Resource 주입받아서 사용할 수 있도록 변경 필요함
class ReviewWriteRepository constructor() {
    //TODO: Wine Repository를 따로 분리해야할 수도?
    fun getReviewWineStream(): Flow<Wine> = flow {
        Wine(
            id = 1L,
            name = "GoldenBlueGoldenBlueGoldenBlueGoldenBlueGoldenBlueGoldenBlueGoldenBlueGoldenBlueGoldenBlueGoldenBlueGoldenBlueGoldenBlueGoldenBlueGoldenBlue",
            imageUrl = R.drawable.img_wine_dummy,
            price = 1000,
            alc = 17,
            listOf("뜨는 술", "맛있는 술", "쓴 술", "단 술"),
            favorite = true,
            category = "와인"
        )
    }
}