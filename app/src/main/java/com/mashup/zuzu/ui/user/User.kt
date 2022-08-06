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
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mashup.zuzu.R
import com.mashup.zuzu.data.model.*
import com.mashup.zuzu.compose.component.*
import com.mashup.zuzu.compose.theme.ProofTheme

/**
 * @Created by 김현국 2022/07/14
 */

@Composable
fun UserRoute(
    viewModel: UserViewModel,
    onClick: (UserUiEvents) -> Unit
) {
    val uiState by viewModel.user.collectAsState() // userData 아마 sharedPreference
    val wineCallerState by viewModel.wineCaller.collectAsState() // wineCallerData
    val joinedWorldCupState by viewModel.joinedWorldCup.collectAsState() // 참여한 술드컵

    when (uiState) {
        is UserUiState.Success -> {
            UserScreen(
                user = (uiState as UserUiState.Success).userData,
                wineCallerState = wineCallerState,
                joinedWorldCupState = joinedWorldCupState,
                onClick = onClick
            )
        }
        is UserUiState.Loading -> {
        }
        is UserUiState.Error -> {
        }
    }
}

@Composable
fun UserScreen(
    modifier: Modifier = Modifier,
    user: User,
    wineCallerState: WineCallerUiState,
    joinedWorldCupState: JoinedWorldCupUiState,
    onClick: (UserUiEvents) -> Unit
) {
    val userState = rememberUserState(user)

    Column(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(color = ProofTheme.color.black)
    ) {
        UserTopBar(
            user = userState.user,
            selectionOption = userState.selectionOption,
            onSelectionChange = userState.onSelectionChange,
            optionList = optionList,
            onSettingClick = {
                onClick(UserUiEvents.SettingButtonClick(userId = userState.user.id))
            },
            onEditButtonClick = {
                onClick(UserUiEvents.EditButtonClick)
            }
        )
        when (userState.selectionOption) {
            optionList[0] -> {
                when (wineCallerState) {
                    is WineCallerUiState.Success -> {
                        WineCaller(
                            modifier = Modifier.fillMaxWidth(),
                            wines = wineCallerState.wineCaller,
                            onWineClick = { wine ->
                                onClick(UserUiEvents.WineItemClick(wine = wine))
                            }
                        )
                    }
                    is WineCallerUiState.NoItem -> {
                        NoItemWineOrWorldCup(
                            modifier = Modifier
                                .fillMaxHeight()
                                .fillMaxWidth(),
                            userName = userState.user.name,
                            type = " 리뷰한 술"
                        )
                    }
                    is WineCallerUiState.Loading -> {
                    }
                    is WineCallerUiState.Error -> {
                    }
                }
            }
            optionList[1] -> {
                when (joinedWorldCupState) {
                    is JoinedWorldCupUiState.Success -> {
                        JoinWorldCup(
                            modifier = Modifier
                                .padding(top = 36.dp, start = 24.dp, end = 24.dp)
                                .fillMaxWidth()
                                .fillMaxHeight(),
                            onWorldCupItemClick = { bestWorldCup ->
                                onClick(UserUiEvents.WorldCupItemClick(bestWorldCup = bestWorldCup))
                            }
                        )
                    }
                    is JoinedWorldCupUiState.NoItem -> {
                        NoItemWineOrWorldCup(
                            modifier = Modifier
                                .fillMaxHeight()
                                .fillMaxHeight(),
                            type = "참여한 술드컵",
                            userName = userState.user.name
                        )
                    }
                    is JoinedWorldCupUiState.Loading -> {
                    }
                    is JoinedWorldCupUiState.Error -> {
                    }
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
    user: User,
    selectionOption: String,
    onSelectionChange: (String) -> Unit,
    optionList: List<String>,
    onSettingClick: () -> Unit,
    onEditButtonClick: () -> Unit
) {
    Column(
        modifier = Modifier.background(color = ProofTheme.color.gray600)
    ) {
        Row(
            modifier = Modifier
                .padding(top = 31.5.dp, end = 24.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            Icon(
                modifier = Modifier
                    .width(20.dp)
                    .height(22.dp)
                    .clickable {
                        onSettingClick()
                    },
                imageVector = Icons.Filled.Settings,
                tint = ProofTheme.color.gray200,
                contentDescription = null
            )
        }
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(31.dp)
        )
        Row(
            modifier = Modifier
                .padding(start = 24.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            Image( // ProfileImage
                modifier = Modifier
                    .width(36.dp)
                    .height(36.dp),
                imageVector = Icons.Filled.Person,
                colorFilter = ColorFilter.tint(ProofTheme.color.gray300),
                contentDescription = null
            )
        }
        Row(
            modifier = Modifier
                .padding(start = 24.dp)
                .fillMaxWidth(),
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
        CustomButtonGroup(
            modifier = Modifier
                .padding(top = 24.dp)
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
    wines: List<Wine>,
    onWineClick: (Wine) -> Unit
) {
    LazyVerticalGrid(
        modifier = modifier.padding(start = 20.dp, end = 20.dp, top = 36.dp),
        columns = GridCells.Fixed(3),
        verticalArrangement = Arrangement.spacedBy(32.dp),
        horizontalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        items(wines) { wine ->
            WineCellarCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
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
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 40.dp),
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
                maxLines = 1
            )
        }
        Box(
            modifier = Modifier
                .background(
                    color = ProofTheme.color.primary200,
                    shape = RoundedCornerShape(8.dp)
                )
                .width(280.dp)
                .height(280.dp)
        )
    }
}

@Preview
@Composable
fun PreviewUserScreen() {
    ProofTheme {
        UserScreen(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            user = user,
            wineCallerState = WineCallerUiState.Loading,
            joinedWorldCupState = JoinedWorldCupUiState.Loading,
            onClick = {}
        )
    }
}

@Preview
@Composable
fun PreviewNoItemWineOrWorldCup() {
    ProofTheme {
        NoItemWineOrWorldCup(
            userName = "위스키다이스키",
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            type = "참여한 술드컵"
        )
    }
}
