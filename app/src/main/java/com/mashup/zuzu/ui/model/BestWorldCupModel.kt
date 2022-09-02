package com.mashup.zuzu.ui.model

/**
 * @Created by 김현국 2022/07/11
 */

data class BestWorldCup(
    val title: String,
    val participants: Int,
    val image: String,
    val winnerDrinkId: Long
)

val bestWorldCupList = listOf(
    BestWorldCup(title = "비오는 날 \n마시고 싶은 술은?", 14444, "https://images.absolutdrinks.com/ingredient-images/Raw/Absolut/65d43459-c926-4b12-a15b-afa7a71c2071.jpg?imwidth=500", 0),
    BestWorldCup(title = "더운 날 \n마시고 싶은 술은?", 14444, "https://images.absolutdrinks.com/ingredient-images/Raw/Absolut/65d43459-c926-4b12-a15b-afa7a71c2071.jpg?imwidth=500", 0),
    BestWorldCup(title = "밤에 \n마시고 싶은 술은?", 14444, "https://images.absolutdrinks.com/ingredient-images/Raw/Absolut/65d43459-c926-4b12-a15b-afa7a71c2071.jpg?imwidth=500", 0),
    BestWorldCup(title = "새벽에 \n마시고 싶은 술은?", 14444, "https://images.absolutdrinks.com/ingredient-images/Raw/Absolut/65d43459-c926-4b12-a15b-afa7a71c2071.jpg?imwidth=500", 0),
    BestWorldCup(title = "적적한 날 \n마시고 싶은 술은?", 14444, "https://images.absolutdrinks.com/ingredient-images/Raw/Absolut/65d43459-c926-4b12-a15b-afa7a71c2071.jpg?imwidth=500", 0),
    BestWorldCup(title = "기쁜 날 \n마시고 싶은 술은?", 14444, "https://images.absolutdrinks.com/ingredient-images/Raw/Absolut/65d43459-c926-4b12-a15b-afa7a71c2071.jpg?imwidth=500", 0)
)

object BestWorldCupRepo {
    fun getBestWorldCupData() = bestWorldCupList.filterIndexed { index, bestWorldCup -> index < 3 }
}
