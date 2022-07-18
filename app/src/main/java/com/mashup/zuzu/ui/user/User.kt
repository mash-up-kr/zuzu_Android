package com.mashup.zuzu.ui.user

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mashup.zuzu.data.model.*
import com.mashup.zuzu.ui.component.*
import com.mashup.zuzu.ui.theme.ProofTheme
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

/**
 * @Created by 김현국 2022/07/14
 * @Time 6:59 오후
 */

@Composable
fun UserRoute(
    viewModel: UserViewModel = viewModel(),
    onSettingClick: (User) -> Unit,
) {
    val uiState by viewModel.user.collectAsState() // userData
    UserScreen(
        modifier = Modifier.fillMaxWidth().fillMaxHeight().background(color = ProofTheme.color.black),
        onSettingClick = onSettingClick,
        uiState = uiState
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun UserScreen(
    modifier: Modifier,
    onSettingClick: (User) -> Unit,
    uiState: UserUiState
) {
    val userState = rememberUserState(
        when (uiState) {
            is UserUiState.Success -> {
                uiState.userData
            } else -> {
                ""
            }
        } as User
    )
    val coroutineScope = rememberCoroutineScope()

    BottomSheetScaffold(
        modifier = modifier,
        sheetBackgroundColor = ProofTheme.color.gray600,
        scaffoldState = userState.bottomSheetState,
        sheetContent = {
            EditProfileBottomSheet(
                modifier = Modifier.padding(start = 24.dp, end = 24.dp, top = 44.dp).fillMaxWidth().height(263.dp).background(color = ProofTheme.color.gray600),
                userName = userState.user.name,
                userNameChange = { name ->
                    userState.updateUserProfileName(name = name)
                }
            )
        }, sheetPeekHeight = 0.dp,
        sheetShape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
    ) {
        Column(modifier = modifier) {
            UserTopBar(
                selectionOption = userState.selectionOption,
                onSelectionChange = userState.onSelectionChange,
                optionList = optionList,
                onSettingClick = { onSettingClick(userState.user) },
                onEditButtonClick = {
                    coroutineScope.launch {
                        userState.changeBottomSheetState()
                    }
                }
            )
            when (userState.selectionOption) {
                optionList[0] -> {
                    when (uiState) {
                        is UserUiState.NoItem -> {
                            NoItemWineOrWorldCup(
                                modifier = Modifier.fillMaxHeight().fillMaxWidth(),
                                userName = userState.user.name,
                                type = "참여한 술드컵"
                            )
                        }
                        is UserUiState.Success -> {
                            WineCaller(
                                modifier = Modifier.fillMaxWidth(),
                                WineRepo.getWine2Data(),
                                onWineClick = {} // 호이스팅 필요
                            )
                        }
                    }
                }
                optionList[1] -> {
                    JoinWorldCup(
                        modifier = Modifier.padding(top = 36.dp, start = 24.dp, end = 24.dp)
                            .fillMaxWidth().fillMaxHeight(),
                        {} // 호이스팅 필요
                    )
                }
            }
        }
    }
}

@Composable
fun JoinWorldCup(
    modifier: Modifier,
    onWorldCupItemClick: (BestWorldCup) -> Unit
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(32.dp)
    ) {
        items(bestWorldCupList) { bestWorldCup ->
            WorldCupCard(
                modifier = Modifier.fillMaxWidth(),
                worldCupItem = bestWorldCup,
                onWorldCupItemClick = onWorldCupItemClick
            )
        }
    }
}
@Composable
fun UserTopBar(
    selectionOption: String,
    optionList: List<String>,
    onSelectionChange: (String) -> Unit,
    onSettingClick: () -> Unit,
    onEditButtonClick: () -> Unit
) {

    Column(
        modifier = Modifier.background(color = ProofTheme.color.gray600)
    ) {
        Row(
            modifier = Modifier.padding(top = 31.5.dp, end = 24.dp).fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            Icon(
                modifier = Modifier.width(20.dp).height(22.dp).clickable {
                    onSettingClick()
                },
                imageVector = Icons.Filled.Settings,
                tint = ProofTheme.color.gray200,
                contentDescription = null
            )
        }
        Spacer(modifier = Modifier.fillMaxWidth().height(31.dp))
        Row(
            modifier = Modifier.padding(start = 24.dp).fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            Image( // ProfileImage
                modifier = Modifier.width(36.dp).height(36.dp),
                imageVector = Icons.Filled.Person,
                colorFilter = ColorFilter.tint(ProofTheme.color.gray300),
                contentDescription = null
            )
        }
        Row(
            modifier = Modifier.padding(start = 24.dp).fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "위스키 다이스키",
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
                imageVector = Icons.Filled.Edit,
                tint = ProofTheme.color.gray400,
                contentDescription = null
            )
        }
        CustomButtonGroup(
            modifier = Modifier.padding(top = 24.dp)
                .height(54.dp)
                .width(170.dp),
            optionList = optionList,
            selectionOption = selectionOption,
            onSelectionChange = onSelectionChange
        )
    }
}

@Composable
fun WineCaller(
    modifier: Modifier,
    wines: List<Wine2>,
    onWineClick: (Wine2) -> Unit
) {
    LazyVerticalGrid(
        modifier = modifier.padding(start = 20.dp, end = 20.dp, top = 36.dp),
        columns = GridCells.Fixed(3),
        verticalArrangement = Arrangement.spacedBy(32.dp),
        horizontalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        items(wines) { wine ->
            WineCellarCard(
                modifier = Modifier.fillMaxWidth().fillMaxHeight(),
                wine = wine,
                onWineClick = onWineClick
            )
        }
    }
}

@Composable
fun NoItemWineOrWorldCup(
    modifier: Modifier,
    type: String,
    userName: String
) {
    Column(
        modifier = modifier.background(color = ProofTheme.color.black),
        verticalArrangement = Arrangement.spacedBy(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(top = 40.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "${type}이 없어요.",
                style = ProofTheme.typography.headingL,
                color = ProofTheme.color.white
            )
            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = "${userName}님의 술경험을 알려주세요.",
                style = ProofTheme.typography.bodyM,
                color = ProofTheme.color.gray200,
                maxLines = 1,
            )
        }
        Box(
            modifier = Modifier
                .background(
                    color = ProofTheme.color.primary200,
                    shape = RoundedCornerShape(8.dp)
                )
                .width(280.dp).height(280.dp)
        )
    }
}

@Composable
fun EditProfileBottomSheet(
    modifier: Modifier,
    userName: String,
    userNameChange: (String) -> Unit
) {
    Column(modifier = modifier) {
        Text(
            text = "프로필 수정하기",
            style = ProofTheme.typography.headingL,
            color = ProofTheme.color.white
        )
        Spacer(modifier = Modifier.fillMaxWidth().height(24.dp))
        ProfileImageItems(
            modifier = Modifier.fillMaxWidth(),
            profileImages = profileImages,
            onProfileImageClick = {},
            selectedProfileImage = profileImages[0]
        )
        Spacer(modifier = Modifier.fillMaxWidth().height(24.dp))
        Text(
            text = "내 별명",
            style = ProofTheme.typography.headingXS,
            color = ProofTheme.color.white
        )
        Spacer(modifier = Modifier.fillMaxWidth().height(6.dp))
        TextField(
            modifier = Modifier.fillMaxWidth().background(color = ProofTheme.color.gray500, shape = RoundedCornerShape(12.dp)),
            value = userName,
            onValueChange = userNameChange,
            textStyle = ProofTheme.typography.bodyM
        )
    }
}
@Preview
@Composable
fun PreviewUserScreen() {
    ProofTheme {
        UserScreen(modifier = Modifier.fillMaxWidth().fillMaxHeight(), {}, uiState = UserUiState.NoItem)
    }
}

@Preview
@Composable
fun PreviewNoItemWineOrWorldCup() {
    ProofTheme {
        NoItemWineOrWorldCup(userName = "위스키다이스키", modifier = Modifier.fillMaxWidth().fillMaxHeight(), type = "참여한 술드컵")
    }
}
