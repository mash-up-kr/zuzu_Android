package com.mashup.zuzu.data.model

import androidx.compose.runtime.Immutable

/**
 * @Created by 김현국 2022/07/15
 */

@Immutable
data class User(
    val id: Long,
    var name: String,
    val email: String,
    val image: String
)
val user = User(
    id = 1L,
    name = "위스키다이스키",
    email = "boris0815@naver.com",
    image = "https://cdn.icon-icons.com/icons2/2468/PNG/512/user_user_thump_user_icon_user_profile_icon_149320.png"
)
val dummyUser = User(
    id = 0L,
    name = "",
    email = "",
    image = ""
)
