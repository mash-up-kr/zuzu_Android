package com.mashup.zuzu.data.model

import androidx.compose.runtime.Immutable
import com.mashup.zuzu.R

/**
 * @Created by 김현국 2022/07/01
 * @Time 3:34 오후
 */

@Immutable
data class Wine(
    val id: Long,
    val name: String,
    val imageUrl: Int,
    val price: Long,
    val alc: Int,
    val description: List<String>,
    val favorite: Boolean,
    val category: String
)

val wines = listOf(
    Wine(
        id = 1L,
        name = "GoldenBlueGoldenBlueGoldenBlueGoldenBlueGoldenBlueGoldenBlueGoldenBlueGoldenBlueGoldenBlueGoldenBlueGoldenBlueGoldenBlueGoldenBlueGoldenBlue",
        imageUrl = R.drawable.img_wine_dummy,
        price = 1000,
        alc = 17,
        listOf("뜨는 술", "맛있는 술", "쓴 술", "단 술"),
        favorite = true,
        category = "와인"
    ),
    Wine(
        id = 2L,
        name = "Jack Daniels",
        imageUrl = R.drawable.img_wine_dummy,
        price = 40000,
        alc = 17,
        listOf("뜨는 술", "맛있는 술"),
        favorite = false,
        category = "양주"
    ),
    Wine(
        id = 3L,
        name = "참이슬",
        imageUrl = R.drawable.img_wine_dummy,
        price = 4500,
        alc = 17,
        listOf("뜨는 술", "맛있는 술"),
        favorite = false,
        category = "소주"
    ),
    Wine(
        id = 4L,
        name = "로즈와인",
        imageUrl = R.drawable.img_wine_dummy,
        price = 24000,
        alc = 17,
        listOf("뜨는 술", "맛있는 술"),
        favorite = false,
        category = "와인"
    ),
    Wine(
        id = 5L,
        name = "진로",
        imageUrl = R.drawable.img_wine_dummy,
        price = 4000,
        alc = 17,
        listOf("뜨는 술", "맛있는 술"),
        favorite = false,
        category = "소주"
    ),
    Wine(
        id = 6L,
        name = "Jim Beam",
        imageUrl = R.drawable.img_wine_dummy,
        price = 24000,
        alc = 17,
        listOf("뜨는 술", "맛있는 술"),
        favorite = false,
        category = "양주"
    ),
    Wine(
        id = 7L,
        name = "Red Wine",
        imageUrl = R.drawable.img_wine_dummy,
        price = 3400,
        alc = 17,
        listOf("뜨는 술", "맛있는 술"),
        favorite = false,
        category = "와인"
    ),
    Wine(
        id = 8L,
        name = "Tintied Wine",
        imageUrl = R.drawable.img_wine_dummy,
        price = 40000,
        alc = 17,
        listOf("뜨는 술", "맛있는 술"),
        favorite = false,
        category = "와인"
    )
)
object WineRepo {
    fun getWineData(): List<Wine> = wines
    fun getWineData(wineId: Long) = wines.find { it.id == wineId }
    fun getWineDataWithCategory(category: String) = wines.filter { it.category == category }
    fun getRecommendWine() = wines[0]
}
