package com.mashup.zuzu.data.model

/**
 * @Created by 김현국 2022/07/11
 * @Time 3:25 오후
 */

data class BestWorldCup(
    val day: String,
    val title: String,
    val participants: Int,
    val image: String
)

val bestWorldCupList = listOf(
    BestWorldCup(day = "2022.08.15", title = "비오는 날 \n마시고 싶은 술은?", 14444, "https://images.absolutdrinks.com/ingredient-images/Raw/Absolut/65d43459-c926-4b12-a15b-afa7a71c2071.jpg?imwidth=500"),
    BestWorldCup(day = "2022.08.15", title = "더운 날 \n마시고 싶은 술은?", 14444, "https://images.absolutdrinks.com/ingredient-images/Raw/Absolut/65d43459-c926-4b12-a15b-afa7a71c2071.jpg?imwidth=500"),
    BestWorldCup(day = "2022.08.15", title = "밤에 \n마시고 싶은 술은?", 14444, "https://images.absolutdrinks.com/ingredient-images/Raw/Absolut/65d43459-c926-4b12-a15b-afa7a71c2071.jpg?imwidth=500"),
    BestWorldCup(day = "2022.08.15", title = "새벽에 \n마시고 싶은 술은?", 14444, "https://images.absolutdrinks.com/ingredient-images/Raw/Absolut/65d43459-c926-4b12-a15b-afa7a71c2071.jpg?imwidth=500"),
    BestWorldCup(day = "2022.08.15", title = "적적한 날 \n마시고 싶은 술은?", 14444, "https://images.absolutdrinks.com/ingredient-images/Raw/Absolut/65d43459-c926-4b12-a15b-afa7a71c2071.jpg?imwidth=500"),
    BestWorldCup(day = "2022.08.15", title = "기쁜 날 \n마시고 싶은 술은?", 14444, "https://images.absolutdrinks.com/ingredient-images/Raw/Absolut/65d43459-c926-4b12-a15b-afa7a71c2071.jpg?imwidth=500")
)

object BestWorldCupRepo {
    fun getBestWorldCupData() = bestWorldCupList.filterIndexed { index, bestWorldCup -> index < 3 }
}
