package com.mashup.zuzu.data.model

import androidx.compose.runtime.Immutable

/**
 * @Created by 김현국 2022/07/01
 */

@Immutable
data class Wine(
    val id: Long,
    val name: String,
    val imageUrl: String,
    val alc: Float,
    val tags: List<String>,
    val description: String?,
    val category: String,
    val worldcupWinCount: Int?,
    val worldcupSemiFinalCount: Int?,
    val origin: String?
)

val wines = listOf(
    Wine(
        id = 1,
        name = "GoldenBlueGoldenBlueGoldenBlueGoldenBlueGoldenBlueGoldenBlueGoldenBlueGoldenBlueGoldenBlueGoldenBlueGoldenBlueGoldenBlueGoldenBlueGoldenBlue",
        imageUrl = "https://images.absolutdrinks.com/ingredient-images/Raw/Absolut/65d43459-c926-4b12-a15b-afa7a71c2071.jpg?imwidth=500",
        alc = 17f,
        tags = listOf("뜨는 술", "맛있는 술", "쓴 술", "단 술"),
        description = "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for 'lorem ipsum' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like).",
        category = "Wine", null, null, null
    ),
    Wine(
        id = 2,
        name = "Jack Daniels",
        imageUrl = "https://images.absolutdrinks.com/ingredient-images/Raw/Absolut/65d43459-c926-4b12-a15b-afa7a71c2071.jpg?imwidth=500",
        alc = 17f,
        tags = listOf("뜨는 술", "맛있는 술"),
        description = "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for 'lorem ipsum' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like).",
        category = "양주", null, null, null
    ),
    Wine(
        id = 3,
        name = "참이슬",
        imageUrl = "https://images.absolutdrinks.com/ingredient-images/Raw/Absolut/65d43459-c926-4b12-a15b-afa7a71c2071.jpg?imwidth=500",
        alc = 17f,
        tags = listOf("뜨는 술", "맛있는 술"),
        description = "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for 'lorem ipsum' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like).",
        category = "소주", null, null, null
    ),
    Wine(
        id = 4,
        name = "로즈와인",
        imageUrl = "https://images.absolutdrinks.com/ingredient-images/Raw/Absolut/65d43459-c926-4b12-a15b-afa7a71c2071.jpg?imwidth=500",
        alc = 17f,
        tags = listOf("뜨는 술", "맛있는 술"),
        description = "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for 'lorem ipsum' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like).",
        category = "와인", null, null, null
    ),
    Wine(
        id = 5,
        name = "진로",
        imageUrl = "https://images.absolutdrinks.com/ingredient-images/Raw/Absolut/65d43459-c926-4b12-a15b-afa7a71c2071.jpg?imwidth=500",
        alc = 17f,
        tags = listOf("뜨는 술", "맛있는 술"),
        description = "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for 'lorem ipsum' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like).",
        category = "소주", null, null, null
    )
)