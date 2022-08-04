package com.mashup.zuzu.data.model

/**
 * @Created by 김현국 2022/07/24
 */
sealed class Results<out R> {
    data class Success<out T>(val value: T) : Results<T>()
    data class Failure(
        val message: String
    ) : Results<Nothing>()

    object Loading : Results<Nothing>()
}
