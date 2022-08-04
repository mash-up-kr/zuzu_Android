package com.mashup.zuzu.ui.user.edit

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mashup.zuzu.ui.component.ProfileImageItems
import com.mashup.zuzu.ui.component.profileImages
import com.mashup.zuzu.ui.theme.ProofTheme
import com.mashup.zuzu.ui.user.UpdateProfileUiEventState
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

    var userName = when (uiState) {
        is UserUiState.Success -> {
            (uiState as UserUiState.Success).userData.name
        }
        else -> {
            ""
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
        userNameChange = {
            userName = it
        },
        onSubmitButtonClick = {
            viewModel.submitUserProfile(userName)
        }
    )
}

@Composable
fun EditProfileBottomSheet(
    modifier: Modifier,
    userName: String,
    userNameChange: (String) -> Unit,
    onSubmitButtonClick: () -> Unit
) {

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
        ProfileImageItems(
            modifier = Modifier.fillMaxWidth(),
            profileImages = profileImages,
            onProfileImageClick = {},
            selectedProfileImage = profileImages[0]
        )
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
                .fillMaxWidth()
                .background(color = ProofTheme.color.gray600, shape = RoundedCornerShape(12.dp)),
            value = userName,
            onValueChange = userNameChange,
            textStyle = ProofTheme.typography.bodyM.copy(color = ProofTheme.color.white),

        )
        Spacer(modifier = Modifier.fillMaxWidth().height(20.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp)
                .background(color = ProofTheme.color.primary300)
                .clickable {
                    onSubmitButtonClick()
                },
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
