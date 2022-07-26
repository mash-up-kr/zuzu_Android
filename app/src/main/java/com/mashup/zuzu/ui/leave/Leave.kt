package com.mashup.zuzu.ui.user

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mashup.zuzu.ui.component.Button
import com.mashup.zuzu.ui.leave.LeaveUiEventState
import com.mashup.zuzu.ui.leave.LeaveViewModel
import com.mashup.zuzu.ui.theme.ProofTheme

/**
 * @Created by 김현국 2022/07/18
 * @Time 5:26 오후
 */

@Composable
fun LeaveRoute(
    viewModel: LeaveViewModel = hiltViewModel(),
    onBackButtonClick: () -> Unit,
    onKeepUsingButtonClick: () -> Unit,
    onLeaveState: (LeaveUiEventState) -> Unit
) {

    val leaveUiEventState = viewModel.leave.collectAsState(initial = LeaveUiEventState.Init)

    LaunchedEffect(leaveUiEventState.value) {
        onLeaveState(leaveUiEventState.value)
    }
    LeaveScreen(
        modifier = Modifier.padding(top = 31.5.dp).fillMaxWidth().fillMaxHeight().background(color = ProofTheme.color.black),
        onBackButtonClick = onBackButtonClick,
        onLeaveButtonClick = {
            viewModel.leaveMembership(userId = 0L) // sharedPre
        },
        onKeepUsingButtonClick = onKeepUsingButtonClick
    )
}

@Composable
fun LeaveScreen(
    modifier: Modifier,
    onBackButtonClick: () -> Unit,
    onLeaveButtonClick: () -> Unit,
    onKeepUsingButtonClick: () -> Unit
) {
    Column(modifier = modifier) {
        LeaveTopBar(
            modifier = Modifier.fillMaxWidth().height(52.dp),
            onBackButtonClick = onBackButtonClick
        )
        Column(
            modifier = Modifier.fillMaxWidth().fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.fillMaxWidth().height(20.dp))
            LeaveTitle(modifier = Modifier.fillMaxWidth().height(96.dp))
            Spacer(modifier = Modifier.fillMaxWidth().height(46.dp))
            LeaveIllus(modifier = Modifier.padding(start = 40.dp, end = 40.dp).width(280.dp).height(324.dp))
            Spacer(modifier = Modifier.fillMaxWidth().height(40.dp))
            Button(
                modifier = Modifier.padding(start = 24.dp, end = 24.dp).fillMaxWidth().height(52.dp),
                text = "탈퇴하기",
                backgroundColor = ProofTheme.color.primary300,
                textColor = ProofTheme.color.white,
                onButtonClick = onLeaveButtonClick
            )
            Spacer(modifier = Modifier.fillMaxWidth().height(25.dp))
            Box(
                modifier = Modifier.padding(start = 24.dp, end = 24.dp).fillMaxWidth().height(40.dp)
                    .clickable {
                        onKeepUsingButtonClick()
                    }
            ) {
                Text(
                    modifier = Modifier.align(Alignment.Center),
                    text = "계속 사용할래요",
                    style = ProofTheme.typography.buttonL,
                    color = ProofTheme.color.primary100,
                )
            }
        }
    }
}

@Composable
fun LeaveTopBar(
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
            text = "탈퇴하기",
            style = ProofTheme.typography.headingXS,
            color = ProofTheme.color.white
        )
    }
}

@Composable
fun LeaveTitle(
    modifier: Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "정말로 탈퇴하시겠어요?",
            style = ProofTheme.typography.headingXL,
            color = ProofTheme.color.white
        )
        Spacer(modifier = Modifier.fillMaxWidth().height(12.dp))
        Text(
            text = "회원님의 정보, 리뷰 등 기록이 전부 삭제되며\n다시는 되돌릴 수 없어요.",
            style = ProofTheme.typography.bodyL,
            color = ProofTheme.color.gray200,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun LeaveIllus(
    modifier: Modifier
) {
    Box(modifier = modifier.background(color = ProofTheme.color.gray200)) {
    }
}

@Preview
@Composable
fun PreviewLeaveTitle() {
    ProofTheme {
        LeaveTitle(modifier = Modifier.fillMaxWidth().height(96.dp))
    }
}
