package com.mashup.zuzu.data.repository

import com.mashup.zuzu.data.model.Wine
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ReviewWriteRepository constructor() {
    // TODO: Wine Repository를 따로 분리해야할 수도?
    fun getReviewWineStream(): Flow<Wine> = flow {
        Wine(
            id = 1L,
            name = "GoldenBlueGoldenBlueGoldenBlueGoldenBlueGoldenBlueGoldenBlueGoldenBlueGoldenBlueGoldenBlueGoldenBlueGoldenBlueGoldenBlueGoldenBlueGoldenBlue",
            imageUrl = "https://images.absolutdrinks.com/ingredient-images/Raw/Absolut/65d43459-c926-4b12-a15b-afa7a71c2071.jpg?imwidth=500",
            alc = 17,
            description = "하이하이하이하이",
            category = "와인",
            tags = listOf("뜨는 술", "맛있는 술", "쓴 술", "단 술")
        )
    }
}
