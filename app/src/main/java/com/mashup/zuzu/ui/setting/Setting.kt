package com.mashup.zuzu.ui.setting

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.mashup.zuzu.R
import com.mashup.zuzu.compose.theme.ProofTheme
import com.mashup.zuzu.data.model.User
import com.mashup.zuzu.data.model.dummyUser
import com.mashup.zuzu.ui.user.UserUiState

/**
 * @Created by 김현국 2022/07/15
 */

@Composable
fun SettingRoute(
    viewModel: SettingViewModel,
    onButtonClick: (SettingUiEvents) -> Unit
) {
    val userState by viewModel.user.collectAsState()
    val networkState by viewModel.connection.collectAsState()

    LaunchedEffect(key1 = networkState) {
        if (networkState) {
            viewModel.getUserData()
        }
    }

    when (userState) {
        is UserUiState.Success -> {
            Setting(
                user = (userState as UserUiState.Success).userData,
                onButtonClick = onButtonClick
            )
        }
        is UserUiState.Loading -> {
            Setting(
                user = dummyUser,
                onButtonClick = {}
            )
        }
        is UserUiState.Error -> {
        }
    }

    val logoutState by viewModel.logout.collectAsState()
    LaunchedEffect(logoutState) {
        when (logoutState) {
            is LogoutUiState.Success -> {
                onButtonClick(SettingUiEvents.MoveToHome)
            }
            is LogoutUiState.Loading -> {}
        }
    }
}

@Composable
fun Setting(
    modifier: Modifier = Modifier,
    user: User,
    onButtonClick: (SettingUiEvents) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        SettingTopBar(
            modifier = Modifier
                .padding(top = 31.5.dp)
                .fillMaxWidth()
                .height(52.dp),
            onBackButtonClick = { onButtonClick(SettingUiEvents.BackButtonClick) }
        )
        SettingUserProfile(
            modifier = Modifier
                .padding(start = 24.dp)
                .fillMaxWidth(),
            user = user,
            onEditButtonClick = { onButtonClick(SettingUiEvents.EditButtonClick) }
        )
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
        )
        SettingBody(
            modifier = Modifier
                .padding(start = 24.dp)
                .fillMaxWidth(),
            onLogoutButtonClick = { onButtonClick(SettingUiEvents.LogoutButtonClick) },
            onLeaveButtonClick = { onButtonClick(SettingUiEvents.LeaveButtonClick) }
        )
    }
}

@Composable
fun SettingTopBar(
    modifier: Modifier,
    onBackButtonClick: () -> Unit
) {
    Box(
        modifier = modifier
    ) {
        Icon(
            modifier = Modifier
                .padding(start = 33.dp)
                .clickable {
                    onBackButtonClick()
                }
                .align(Alignment.CenterStart),
            painter = painterResource(id = R.drawable.ic_arrow_left),
            tint = ProofTheme.color.white,
            contentDescription = null
        )
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = stringResource(id = R.string.setting),
            style = ProofTheme.typography.headingXS,
            color = ProofTheme.color.white
        )
    }
}

@Composable
fun SettingUserProfile(
    modifier: Modifier,
    user: User,
    onEditButtonClick: () -> Unit
) {
    Column(modifier = modifier.padding(top = 16.dp)) {
        AsyncImage(
            modifier = Modifier
                .width(36.dp)
                .height(36.dp)
                .clip(RoundedCornerShape(8.dp)),
            model = user.image,
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = user.name,
                style = ProofTheme.typography.headingL,
                color = ProofTheme.color.white
            )
            Icon(
                modifier = Modifier
                    .padding(start = 10.dp)
                    .width(12.dp)
                    .height(12.dp)
                    .clickable {
                        onEditButtonClick()
                    },
                painter = painterResource(id = R.drawable.ic_edit),
                tint = ProofTheme.color.gray200,
                contentDescription = null
            )
        }
        if (user.email != null) {
            Text(
                modifier = Modifier.padding(top = 5.dp),
                text = user.email,
                style = ProofTheme.typography.bodyXS,
                color = ProofTheme.color.primary50
            )
        }
    }
}

@Composable
fun SettingBody(
    modifier: Modifier,
    onLogoutButtonClick: () -> Unit,
    onLeaveButtonClick: () -> Unit
) {
    Column(modifier = modifier) {
        Box(modifier = Modifier.height(30.dp)) {
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = stringResource(id = R.string.setting_service),
                style = ProofTheme.typography.bodyXS,
                color = ProofTheme.color.gray200
            )
        }
        Text(
            modifier = Modifier.padding(top = 20.dp),
            text = stringResource(id = R.string.setting_information_to_use),
            style = ProofTheme.typography.headingS,
            color = ProofTheme.color.white
        )
        Text(
            modifier = Modifier.padding(top = 24.dp),
            text = stringResource(id = R.string.setting_contact_us),
            style = ProofTheme.typography.headingS,
            color = ProofTheme.color.white
        )
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
        )
        Text(
            text = stringResource(id = R.string.setting_auth),
            style = ProofTheme.typography.bodyXS,
            color = ProofTheme.color.gray200
        )
        Text(
            modifier = Modifier
                .padding(top = 20.dp)
                .clickable {
                    onLogoutButtonClick()
                },
            text = stringResource(id = R.string.setting_logout),
            style = ProofTheme.typography.headingS,
            color = ProofTheme.color.gray200
        )
        Text(
            modifier = Modifier
                .padding(top = 24.dp)
                .clickable {
                    onLeaveButtonClick()
                },
            text = stringResource(id = R.string.setting_remove_membership),
            style = ProofTheme.typography.headingS,
            color = ProofTheme.color.gray200
        )
    }
}

@Preview
@Composable
fun PreviewSettingTopBar() {
    ProofTheme {
        SettingTopBar(
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp),
            {}
        )
    }
}

@Preview
@Composable
fun PreviewSettingBody() {
    ProofTheme {
        SettingBody(modifier = Modifier.fillMaxWidth(), {}, {})
    }
}
