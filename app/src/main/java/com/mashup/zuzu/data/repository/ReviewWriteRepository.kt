package com.mashup.zuzu.data.repository

import com.mashup.zuzu.data.model.OptionWithEmoji
import com.mashup.zuzu.data.model.ReviewOption
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

//TODO: String Resource 주입받아서 사용할 수 있도록 변경 필요함
class ReviewWriteRepository constructor() {
    fun getSelectionWithTopic(pageNum: Int): Flow<ReviewOption> {
        val a = when (pageNum) {
            0 -> {
                flow {
                    val option = selectWeatherOptions()
                    emit(ReviewOption("이 술을 마셨던 날의\n날씨는...", option))
                }
            }

            1 -> {
                flow {
                    val option = selectDateOptions()
                    emit(ReviewOption("이 술을 마셨던\n시간은...", option))
                }
            }

            2 -> {
                flow {
                    val option = selectWhoOptions()
                    emit(ReviewOption("누구와 마셨나요?", option))
                }
            }

            else -> {
                flow {
                    val option = selectWeatherOptions()
                    emit(ReviewOption("topic", option))
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