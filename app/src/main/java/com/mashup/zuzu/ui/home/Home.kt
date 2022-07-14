package com.mashup.zuzu.ui.home

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mashup.zuzu.R
import com.mashup.zuzu.data.model.BestWorldCup
import com.mashup.zuzu.data.model.Wine
import com.mashup.zuzu.data.model.wines
import com.mashup.zuzu.ui.category.Category
import com.mashup.zuzu.ui.component.*
import com.mashup.zuzu.ui.theme.ProofTheme

/**
 * @Created by 김현국 2022/06/30
 * @Time 5:08 오후
 */

sealed class BottomScreen(val route: String) {
    object Navigation : BottomScreen("Navigation")
    object User : BottomScreen("User")
    object WorldCup : BottomScreen("WorldCup")
    object Category : BottomScreen("Category")
}

val bottomNavigationItems = listOf(
    BottomScreen.Navigation,
    BottomScreen.User
)

@Composable
fun HomeLogo(modifier: Modifier) {
    Image(
        painterResource(id = R.drawable.ic_logo),
        contentDescription = null,
        modifier = modifier
    )
}

@Composable
fun HomeMainTitle(
    modifier: Modifier,
    onSuggestionClick: () -> Unit
) {
    Row {
        Text(
            modifier = modifier,
            style = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            ),
            color = ProofTheme.color.white,
            text = "요즘 사람들은 \n어떤 술을 마실까?"
        )
        Image(
            painter = painterResource(id = R.drawable.img_wine_logo),
            contentDescription = null,
            modifier = Modifier
                .clickable { onSuggestionClick() }
                .align(Alignment.Bottom)
                .width(36.dp)
                .height(36.dp)
        )
    }
}

@Composable
fun HomeMainTitleItems(
    wines: List<Wine>,
    modifier: Modifier,
    onWineBoardClick: (Wine) -> Unit
) {
    LazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        itemsIndexed(wines) { index, wine ->
            WineCardInHome(
                modifier = Modifier.width(224.dp),
                height = 260.dp,
                wine = wines[index], onWineBoardClick = onWineBoardClick
            )
        }
    }
}

@Composable
fun HomeSubTitle(
    modifier: Modifier,
    boldTitle: String
) {
    Column(modifier = modifier) {
        Text(
            text = boldTitle,
            style = ProofTheme.typography.headingL,
            color = ProofTheme.color.white
        )
    }
}

@Composable
fun HomeBestWorldCup(
    modifier: Modifier,
    bestWorldCupList: List<BestWorldCup>,
    onWorldCupItemClick: (BestWorldCup) -> Unit
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(32.dp)
    ) {
        bestWorldCupList.forEach { bestWorldCup ->
            WorldCupCard(
                modifier = Modifier.fillMaxWidth(),
                worldCupItem = bestWorldCup,
                onWorldCupItemClick = onWorldCupItemClick
            )
        }
    }
}

@Composable
fun ZuzuHomeScreen(
    modifier: Modifier,
    onCategoryClick: (Category) -> Unit,
    onWorldCupItemClick: (BestWorldCup) -> Unit,
    onWineBoardClick: (Wine) -> Unit,
    homeViewModel: HomeViewModel = viewModel(),
) {
    val scrollState = rememberScrollState()
    val bestWorldCupState = homeViewModel.bestWorldCupList.collectAsState().value
    val recommendState = homeViewModel.recommendWine.collectAsState().value
    val mainWineState = homeViewModel.mainWineList.collectAsState().value

    Column(
        modifier = modifier.verticalScroll(scrollState)
    ) {
        HomeLogo(modifier = Modifier.padding(top = 24.dp, start = 24.dp))
        HomeMainTitle(modifier = Modifier.padding(top = 31.dp, start = 24.dp), {})
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(24.dp)
        )
        when (mainWineState) {
            is MainWineUiState.Success -> {
                Box(
                    modifier = Modifier.fillMaxWidth().height(372.dp)
                ) {
                    HomeMainTitleItems(
                        modifier = Modifier.padding(start = 24.dp),
                        wines = mainWineState.mainWines,
                        onWineBoardClick = onWineBoardClick
                    )
                }
            }
            is MainWineUiState.Error -> {}
            is MainWineUiState.Loading -> {}
        }
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(32.dp)
        )
        ColorSpacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(6.dp),
            color = ProofTheme.color.black
        )
        HomeSubTitle(
            modifier = Modifier.padding(start = 24.dp, top = 32.dp),
            boldTitle = "무엇을 마실지 고민이라면?"
        )
        CategoryItems(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 24.dp, top = 24.dp),
            onCategoryClick = onCategoryClick
        )
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(32.dp)
        )
        ColorSpacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(6.dp),
            color = ProofTheme.color.black
        )
        // 오늘의 추천 술
        HomeSubTitle(
            modifier = Modifier.padding(start = 24.dp, top = 40.dp),
            boldTitle = "오늘의 추천 술"
        )

        when (recommendState) {
            is RecommendWineUiState.Success -> {
                RecommendWineCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(367.dp)
                        .padding(start = 24.dp, end = 24.dp, top = 19.dp)
                        .clip(RoundedCornerShape(16.dp)),
                    recommendWine = recommendState.recommendWine,
                    onRefreshButtonClick = {
                        homeViewModel.getRecommendWine()
                    }
                )
            }
            is RecommendWineUiState.Loading -> {
                RecommendImage(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(367.dp)
                        .padding(start = 24.dp, end = 24.dp, top = 19.dp),
                    onButtonClick = {
                        homeViewModel.getRecommendWine()
                    }
                )
            }
            is RecommendWineUiState.Error -> {}
        }

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(42.dp)
        )
        ColorSpacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(6.dp),
            color = ProofTheme.color.black
        )
        HomeSubTitle(
            modifier = Modifier.padding(start = 24.dp, top = 32.dp),
            boldTitle = "지금 가장 인기있는 술드컵"
        )
        when (bestWorldCupState) {
            is BestWorldCupUiState.Loading -> {
            }
            is BestWorldCupUiState.Error -> {
            }
            is BestWorldCupUiState.Success -> {
                HomeBestWorldCup(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .padding(top = 24.dp, start = 24.dp, end = 24.dp),
                    bestWorldCupList = bestWorldCupState.bestWorldCupList,
                    onWorldCupItemClick = onWorldCupItemClick
                )
            }
        }
    }
}

@Composable
fun ZuzuBottomNavigationBar(
    currentRoute: String?,
    onBottomTabsClick: (String) -> Unit,
    bottomNavigationItems: List<BottomScreen>
) {
    BottomNavigation(
        modifier = Modifier
            .clip(
                RoundedCornerShape(24.dp, 24.dp, 0.dp, 0.dp)
            )
            .height(52.dp),
        backgroundColor = ProofTheme.color.black
    ) {
        bottomNavigationItems.forEach { screen ->
            BottomNavigationItem(
                icon = {
                    when (screen.route) {
                        "Navigation" -> {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_compass),
                                contentDescription = null
                            )
                        }
                        "User" -> {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_user),
                                contentDescription = null
                            )
                        }
                    }
                },
                selected = currentRoute == screen.route, // 선택에 따라서 색상이 변경됩니다.
                selectedContentColor = ProofTheme.color.white,
                alwaysShowLabel = false,
                onClick = {
                    onBottomTabsClick(screen.route)
                }
            )
        }
    }
}

@Composable
fun RecommendImage(
    modifier: Modifier,
    onButtonClick: () -> Unit
) {
    Box(modifier = modifier.clip(shape = RoundedCornerShape(8.dp))) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            painter = painterResource(id = R.drawable.img_recommend),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        Button(
            modifier = Modifier
                .padding(bottom = 24.dp)
                .width(115.dp)
                .height(44.dp)
                .align(Alignment.BottomCenter),
            text = "추천술 보기", onButtonClick = onButtonClick
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewZuzuHomeScreen() {
    ProofTheme {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            ZuzuHomeScreen(
                modifier = Modifier.background(color = ProofTheme.color.black),
                {},
                {},
                {},
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewZuzuNavigationBar() {
    ProofTheme() {
        ZuzuBottomNavigationBar(
            currentRoute = null,
            onBottomTabsClick = {},
            bottomNavigationItems = bottomNavigationItems
        )
    }
}

@Preview(showBackground = false)
@Composable
fun PreviewZuzuWineCategoryNavigationTitle() {
    ProofTheme {
        HomeSubTitle(
            modifier = Modifier.padding(start = 24.dp, top = 32.dp),
            boldTitle = "무엇을 마실지 고민이라면?"
        )
    }
}

@Preview(showBackground = false)
@Composable
fun PreviewZuzuWineCategoryItems() {
    ProofTheme() {
        CategoryItems(modifier = Modifier.fillMaxWidth(), onCategoryClick = {})
//
    }
}

@Preview
@Composable
fun PreviewRecommendImage() {
    ProofTheme {
        Column {
            HomeSubTitle(
                modifier = Modifier.padding(start = 24.dp, top = 40.dp),
                boldTitle = "오늘의 추천 술"
            )
            RecommendImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(367.dp)
                    .padding(start = 24.dp, end = 24.dp),
                {}
            )
        }
    }
}
