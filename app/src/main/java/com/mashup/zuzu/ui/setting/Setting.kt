package com.mashup.zuzu.ui.user

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.mashup.zuzu.data.model.User
import com.mashup.zuzu.data.model.UserRepo
import com.mashup.zuzu.ui.setting.SettingViewModel
import com.mashup.zuzu.ui.theme.ProofTheme

/**
 * @Created by 김현국 2022/07/15
 * @Time 5:22 오후
 */

@Composable
fun SettingRoute(
    settingViewModel: SettingViewModel = viewModel(),
    userId: Long,
    onBackButtonClick: () -> Unit,
    onLeaveButtonClick: () -> Unit,
    onEditButtonClick: () -> Unit
) {
    // viewModel 넣기
    Setting(
        userId = userId,
        onBackButtonClick = onBackButtonClick,
        onLeaveButtonClick = onLeaveButtonClick,
        onEditButtonClick = onEditButtonClick
    )
}
@Composable
fun Setting(
    userId: Long,
    onBackButtonClick: () -> Unit,
    onLeaveButtonClick: () -> Unit,
    onEditButtonClick: () -> Unit
) {
    Column {
        SettingTopBar(
            modifier = Modifier.padding(top = 31.5.dp).fillMaxWidth().height(52.dp),
            onBackButtonClick = onBackButtonClick
        )
        SettingUserProfile(
            modifier = Modifier.padding(start = 24.dp).fillMaxWidth(),
            user = UserRepo.getUser(userId),
            onEditButtonClick = onEditButtonClick
        )
        Spacer(modifier = Modifier.fillMaxWidth().height(48.dp))
        SettingBody(
            modifier = Modifier.padding(start = 24.dp).fillMaxWidth(),
            onLeaveButtonClick = onLeaveButtonClick
        )
    }
}

@Composable
fun SettingTopBar(
    modifier: Modifier,
    onBackButtonClick: () -> Unit
) {
    Box(
        modifier = modifier,
    ) {
        Icon(
            modifier = Modifier
                .padding(start = 33.dp).clickable {
                    onBackButtonClick()
                }.align(Alignment.CenterStart),
            imageVector = Icons.Outlined.ArrowBack,
            tint = ProofTheme.color.white,
            contentDescription = null
        )
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = "설정",
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
            modifier = Modifier.width(36.dp).height(36.dp).clip(RoundedCornerShape(8.dp)),
            model = ImageRequest.Builder(LocalContext.current)
                .data(user.image).build(),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        Row(
            modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = user.name,
                style = ProofTheme.typography.headingL,
                color = ProofTheme.color.white
            )
            Icon(
                modifier = Modifier.padding(start = 10.dp).width(12.dp).height(12.dp)
                    .clickable {
                        onEditButtonClick()
                    },
                imageVector = Icons.Filled.Edit,
                tint = ProofTheme.color.gray400,
                contentDescription = null
            )
        }
        Text(
            modifier = Modifier.padding(top = 5.dp),
            text = user.email,
            style = ProofTheme.typography.bodyXS,
            color = ProofTheme.color.primary50
        )
    }
}

@Composable
fun SettingBody(
    modifier: Modifier,
    onLeaveButtonClick: () -> Unit
) {
    Column(modifier = modifier) {
        Box(modifier = Modifier.height(30.dp)) {
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = "서비스 이용",
                style = ProofTheme.typography.bodyXS,
                color = ProofTheme.color.gray200,
            )
        }
        Text(
            modifier = Modifier.padding(top = 20.dp),
            text = "이용 안내",
            style = ProofTheme.typography.headingS,
            color = ProofTheme.color.white
        )
        Text(
            modifier = Modifier.padding(top = 24.dp),
            text = "문의 하기",
            style = ProofTheme.typography.headingS,
            color = ProofTheme.color.white
        )
        Spacer(modifier = Modifier.fillMaxWidth().height(56.dp))
        Text(
            text = "계정",
            style = ProofTheme.typography.bodyXS,
            color = ProofTheme.color.gray200,
        )
        Text(
            modifier = Modifier.padding(top = 20.dp),
            text = "로그아웃",
            style = ProofTheme.typography.headingS,
            color = ProofTheme.color.gray200
        )
        Text(
            modifier = Modifier.padding(top = 24.dp).clickable {
                onLeaveButtonClick()
            },
            text = "탈퇴하기",
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
            modifier = Modifier.fillMaxWidth().height(52.dp), {}
        )
    }
}

@Preview
@Composable
fun PreviewSettingScreen() {
    ProofTheme {
        Setting(1, {}, {}, {})
    }
}

@Preview
@Composable
fun PreviewSettingBody() {
    ProofTheme {
        SettingBody(modifier = Modifier.fillMaxWidth(), {})
    }
}
