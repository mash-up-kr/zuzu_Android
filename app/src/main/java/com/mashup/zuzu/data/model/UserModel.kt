package com.mashup.zuzu.data.model

import androidx.compose.runtime.Immutable
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * @Created by 김현국 2022/07/15
 * @Time 5:38 오후
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
object UserRepo {
    fun getUserData(): Flow<User> = flow {
        emit(user)
    }
}
