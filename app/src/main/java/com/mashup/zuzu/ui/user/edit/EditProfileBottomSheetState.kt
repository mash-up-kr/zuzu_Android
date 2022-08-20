package com.mashup.zuzu.ui.user.edit

import androidx.compose.runtime.*

/**
 * @Created by 김현국 2022/08/16
 */

@Stable
class EditProfileBottomSheetState(
    initSelectedImagesIndex: Int,
    initUserName: String
) {
    var selectedImagesIndex by mutableStateOf(initSelectedImagesIndex)
    var currentUserName by mutableStateOf(initUserName)

    fun updateSelectedImageIndex(index: Int) {
        selectedImagesIndex = index
    }

    fun updateCurrentUserName(name: String) {
        currentUserName = name
    }
}

@Composable
fun rememberEditProffileBottomSheetState(
    index: Int,
    userName: String
): EditProfileBottomSheetState = remember {
    EditProfileBottomSheetState(
        initSelectedImagesIndex = index,
        initUserName = userName
    )
}
