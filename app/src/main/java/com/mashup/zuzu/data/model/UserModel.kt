package com.mashup.zuzu.data.model

import androidx.compose.runtime.Immutable

/**
 * @Created by 김현국 2022/07/15
 */

@Immutable
data class User(
    val id: Long,
    var name: String,
    val email: String?,
    val image: String
)

val dummyUser = User(
    id = 0L,
    name = "",
    email = "",
    image = ""
)
