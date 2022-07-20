package com.mashup.zuzu.data.repository

import com.mashup.zuzu.R
import com.mashup.zuzu.data.model.OptionWithEmoji
import com.mashup.zuzu.data.model.ReviewWriteSelectOption
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

    fun getSelectionWithTopic(pageNum: Int): Flow<ReviewWriteSelectOption> {
        val a = when (pageNum) {
            0 -> {
                flow {
                    val option = selectWeatherOptions()
                    emit(ReviewWriteSelectOption("이 술을 마셨던 날의\n날씨는...", option))
                }
            }

            1 -> {
                flow {
                    val option = selectDateOptions()
                    emit(ReviewWriteSelectOption("이 술을 마셨던\n시간은...", option))
                }
            }

            2 -> {
                flow {
                    val option = selectWhoOptions()
                    emit(ReviewWriteSelectOption("누구와 마셨나요?", option))
                }
            }

            else -> {
                flow {
                    val option = selectWeatherOptions()
                    emit(ReviewWriteSelectOption("topic", option))
                }
            }
        }

        return a
    }

    private fun selectWeatherOptions(): List<OptionWithEmoji> {
        return listOf(
            OptionWithEmoji(emoji = null, content = "비 오는 날"),
            OptionWithEmoji(emoji = null, content = "눈 오는 날"),
            OptionWithEmoji(emoji = null, content = "맑은 날"),
            OptionWithEmoji(emoji = null, content = "흐린 날")
        )
    }

    private fun selectDateOptions(): List<OptionWithEmoji> {
        return listOf(
            OptionWithEmoji(emoji = null, content = "저녁에"),
            OptionWithEmoji(emoji = null, content = "대낮에"),
            OptionWithEmoji(emoji = null, content = "한밤중에"),
            OptionWithEmoji(emoji = null, content = "새벽에")
        )
    }

    private fun selectWhoOptions(): List<OptionWithEmoji> {
        return listOf(
            OptionWithEmoji(emoji = null, content = "혼자서"),
            OptionWithEmoji(emoji = null, content = "친구/지인과"),
            OptionWithEmoji(emoji = null, content = "연인과"),
            OptionWithEmoji(emoji = null, content = "회식/단체 모임")
        )
    }
}