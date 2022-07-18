package com.mashup.zuzu.ui.user

import androidx.compose.material.*
import androidx.compose.runtime.*
import com.mashup.zuzu.data.model.User
import kotlinx.coroutines.coroutineScope

/**
 * @Created by 김현국 2022/07/15
 * @Time 1:59 오후
 */

val optionList = listOf("술 저장고", "참여한 술드컵")

@Stable
@OptIn(ExperimentalMaterialApi::class)
class UserState constructor(
    initSelectionOption: String,
    initUser: User,
    bottomSheetScaffoldState: BottomSheetScaffoldState
) {
    var selectionOption by mutableStateOf(initSelectionOption)
    var bottomSheetState by mutableStateOf(bottomSheetScaffoldState)
    var user by mutableStateOf(initUser)

    var onSelectionChange = { text: String ->
        selectionOption = text
    }

    fun updateUserProfileName(name: String) {
        user.name = name
    }

    suspend fun changeBottomSheetState() {
        coroutineScope {
            if (bottomSheetState.bottomSheetState.isCollapsed) {
                bottomSheetState.bottomSheetState.expand()
            } else {
                bottomSheetState.bottomSheetState.collapse()
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun rememberUserState(
    initUser: User,
    bottomSheetScaffoldState: BottomSheetScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = BottomSheetState(BottomSheetValue.Collapsed)
    )
): UserState = remember {
    UserState(
        initSelectionOption = optionList[0],
        initUser = initUser,
        bottomSheetScaffoldState = bottomSheetScaffoldState
    )
}
