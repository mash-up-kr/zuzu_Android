package com.mashup.zuzu.data.model

/**
 * @Created by 김현국 2022/07/24
 * @Time 5:03 오후
 * @Description api 결과 -> 위치 조정 필요
 */
sealed class Results<out R> {
    data class Success<out T>(val value: T) : Results<T>()
    data class Failure(
        val message: String
    ) : Results<Nothing>()

    object Loading : Results<Nothing>()
}
