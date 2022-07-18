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
    BestWorldCup(day = "2022.08.15", title = "비오는 날 \n마시고 싶은 술은?", 14444, "https://d1sqi8cd60mbpv.cloudfront.net/w400/stella_artois_w400.jpg"),
    BestWorldCup(day = "2022.08.15", title = "더운 날 \n마시고 싶은 술은?", 14444, "https://d1sqi8cd60mbpv.cloudfront.net/w400/stella_artois_w400.jpg"),
    BestWorldCup(day = "2022.08.15", title = "밤에 \n마시고 싶은 술은?", 14444, "https://d1sqi8cd60mbpv.cloudfront.net/w400/stella_artois_w400.jpg"),
    BestWorldCup(day = "2022.08.15", title = "새벽에 \n마시고 싶은 술은?", 14444, "https://d1sqi8cd60mbpv.cloudfront.net/w400/stella_artois_w400.jpg"),
    BestWorldCup(day = "2022.08.15", title = "적적한 날 \n마시고 싶은 술은?", 14444, "https://d1sqi8cd60mbpv.cloudfront.net/w400/stella_artois_w400.jpg"),
    BestWorldCup(day = "2022.08.15", title = "기쁜 날 \n마시고 싶은 술은?", 14444, "https://d1sqi8cd60mbpv.cloudfront.net/w400/stella_artois_w400.jpg")
)

object BestWorldCupRepo {
    fun getBestWorldCupData() = bestWorldCupList.filterIndexed { index, bestWorldCup -> index < 3 }
}
