package com.mashup.zuzu.data.model

import androidx.compose.runtime.Immutable
import com.mashup.zuzu.R

/**
 * @Created by 김현국 2022/06/21
 * @Time 1:45 오후
 */

@Immutable
data class Wine(
    val id: Long,
    val name: String,
    val imageUrl: Int,
    val price: Long,
    val alc: Int,
    val description: List<String>
)

val wines = listOf(
    Wine(
        id = 1L,
        name = "GoldenBlue",
        imageUrl = R.drawable.goldenblue,
        price = 1000,
        alc = 17,
        listOf("뜨는 술", "맛있는 술")
    ),
    Wine(
        id = 2L,
        name = "Jack Daniels",
        imageUrl = R.drawable.jackdaniels,
        price = 40000,
        alc = 17,
        listOf("뜨는 술", "맛있는 술")
    ),
    Wine(
        id = 3L,
        name = "참이슬",
        imageUrl = R.drawable.truewine,
        price = 4500,
        alc = 17,
        listOf("뜨는 술", "맛있는 술")
    ),
    Wine(
        id = 4L,
        name = "로즈와인",
        imageUrl = R.drawable.rosewine,
        price = 24000,
        alc = 17,
        listOf("뜨는 술", "맛있는 술")
    ),
    Wine(
        id = 5L,
        name = "진로",
        imageUrl = R.drawable.jinlo,
        price = 4000,
        alc = 17,
        listOf("뜨는 술", "맛있는 술")
    ),
    Wine(
        id = 6L,
        name = "Jim Beam",
        imageUrl = R.drawable.jimbeam,
        price = 24000,
        alc = 17,
        listOf("뜨는 술", "맛있는 술")
    ),
    Wine(
        id = 7L,
        name = "Red Wine",
        imageUrl = R.drawable.redwine,
        price = 3400,
        alc = 17,
        listOf("뜨는 술", "맛있는 술")
    ),
    Wine(
        id = 8L,
        name = "Tintied Wine",
        imageUrl = R.drawable.tintiedwine,
        price = 40000,
        alc = 17,
        listOf("뜨는 술", "맛있는 술")
    )
)
object WineRepo {
    fun getWineData(): List<Wine> = wines
    fun getWineData(wineId: Long) = wines.find { it.id == wineId }
}
