package com.mashup.zuzu.ui.user.edit

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.mashup.zuzu.compose.component.ProfileImageItems
import com.mashup.zuzu.compose.theme.ProofTheme
import com.mashup.zuzu.ui.user.UpdateProfileUiEventState
import com.mashup.zuzu.ui.user.UserProfileImagesUiState
import com.mashup.zuzu.ui.user.UserUiState
import com.mashup.zuzu.ui.user.UserViewModel

/**
 * @Created by 김현국 2022/07/21
 */

@Composable
fun EditUserProfileRoute(
    viewModel: UserViewModel,
    onSubmitState: (UpdateProfileUiEventState) -> Unit
) {
    val uiState by viewModel.user.collectAsState() // userData
    val submitState by viewModel.submit.collectAsState()
    val userProfileImagesUiState by viewModel.userProfileImages.collectAsState()
    val networkState by viewModel.connection.collectAsState()

    val userName = when (uiState) {
        is UserUiState.Success -> {
            (uiState as UserUiState.Success).userData.name
        }
        else -> {
            ""
        }
    }

    LaunchedEffect(networkState) {
        if (networkState) {
            viewModel.getUserProfileImages()
        }
    }

    LaunchedEffect(key1 = submitState) {
        onSubmitState(submitState)
    }

    EditProfileBottomSheet(
        modifier = Modifier
            .padding(start = 24.dp, end = 24.dp, top = 44.dp)
            .fillMaxWidth()
            .wrapContentHeight().navigationBarsPadding()
            .background(color = ProofTheme.color.gray600),
        userName = userName,
        onSubmitButtonClick = { userName, index ->
            viewModel.submitUserProfile(name = userName, index = index)
        },
        userProfileImagesUiState = userProfileImagesUiState
    )
}

@Composable
fun EditProfileBottomSheet(
    modifier: Modifier,
    userName: String,
    onSubmitButtonClick: (String, Long) -> Unit,
    userProfileImagesUiState: UserProfileImagesUiState
) {
    val editProfileBottomSheetState = rememberEditProffileBottomSheetState(index = 0, userName = userName)

    Column(modifier = modifier) {
        Text(
            text = "프로필 수정하기",
            style = ProofTheme.typography.headingL,
            color = ProofTheme.color.white
        )
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(24.dp)
        )
        when (userProfileImagesUiState) {
            is UserProfileImagesUiState.Success -> {
                ProfileImageItems(
                    modifier = Modifier.fillMaxWidth(),
                    profileImages = userProfileImagesUiState.userProfileImages,
                    onProfileImageClick = { index ->
                        editProfileBottomSheetState.updateSelectedImageIndex(index)
                    },
                    selectedProfileImage = userProfileImagesUiState.userProfileImages[editProfileBottomSheetState.selectedImagesIndex]
                )
            }
            is UserProfileImagesUiState.Loading -> {
            }
        }
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(24.dp)
        )
        Text(
            text = "내 별명",
            style = ProofTheme.typography.headingXS,
            color = ProofTheme.color.white
        )
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(6.dp)
        )
        TextField(
            modifier = Modifier
                .fillMaxWidth(),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = ProofTheme.color.gray500,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            ),
            shape = RoundedCornerShape(12.dp),
            value = editProfileBottomSheetState.currentUserName,
            onValueChange = { name ->
                editProfileBottomSheetState.updateCurrentUserName(name)
            },
            textStyle = ProofTheme.typography.bodyM.copy(color = ProofTheme.color.white)
        )
        Spacer(modifier = Modifier.fillMaxWidth().height(20.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp)
                .background(color = ProofTheme.color.primary300)
                .clickable {
                    onSubmitButtonClick(
                        editProfileBottomSheetState.currentUserName,
                        (userProfileImagesUiState as UserProfileImagesUiState.Success).userProfileImages[editProfileBottomSheetState.selectedImagesIndex].id
                    )
                }
        ) {
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = "확인",
                style = ProofTheme.typography.buttonL,
                color = ProofTheme.color.white,
                textAlign = TextAlign.Center
            )
        }
    }
}
