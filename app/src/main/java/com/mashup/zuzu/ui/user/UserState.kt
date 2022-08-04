package com.mashup.zuzu.ui.user

import androidx.compose.runtime.*
import com.mashup.zuzu.data.model.User

/**
 * @Created by 김현국 2022/07/15
 */

val optionList = listOf("술 저장고", "참여한 술드컵")

@Stable
class UserState constructor(
    initSelectionOption: String,
    initUser: User
) {
    var selectionOption by mutableStateOf(initSelectionOption)
    var user by mutableStateOf(initUser)

    var onSelectionChange = { text: String ->
        selectionOption = text
    }
}

@Composable
fun rememberUserState(
    initUser: User

): UserState = remember {
    UserState(
        initSelectionOption = optionList[0],
        initUser = initUser
    )
}
