package com.mashup.zuzu.ui.home

import android.content.Context
import android.graphics.Bitmap
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import com.mashup.zuzu.R
import com.mashup.zuzu.data.model.BestWorldCup
import com.mashup.zuzu.data.model.Wine
import com.mashup.zuzu.ui.component.*
import com.mashup.zuzu.ui.model.Category
import com.mashup.zuzu.ui.theme.ProofTheme

/**
 * @Created by 김현국 2022/06/30
 * @Time 5:08 오후
 */

@Composable
fun HomeRoute(
    viewModel: HomeViewModel = viewModel(),
    onWineBoardClick: (Wine) -> Unit,
    onWorldCupItemClick: (BestWorldCup) -> Unit,
    onCategoryClick: (Category) -> Unit
) {

    val bestWorldCupState = viewModel.bestWorldCupList.collectAsState().value
    val recommendState = viewModel.recommendWine.collectAsState().value
    val mainWineState = viewModel.mainWineList.collectAsState().value
    val blurBitmap = viewModel.bitmap.collectAsState().value

    HomeScreen(
        modifier = Modifier.fillMaxWidth().fillMaxHeight().background(color = ProofTheme.color.black),
        onCategoryClick = onCategoryClick,
        onWorldCupItemClick = onWorldCupItemClick,
        onWineBoardClick = onWineBoardClick,
        onRefreshButtonClick = { context ->
            viewModel.getRecommendWine(context = context)
        },
        bestWorldCupState = bestWorldCupState,
        recommendState = recommendState,
        mainWineState = mainWineState,
        blurBitmap = blurBitmap
    )
}

@Composable
fun HomeScreen(
    modifier: Modifier,
    onCategoryClick: (Category) -> Unit,
    onWorldCupItemClick: (BestWorldCup) -> Unit,
    onWineBoardClick: (Wine) -> Unit,
    onRefreshButtonClick: (Context) -> Unit,
    bestWorldCupState: BestWorldCupUiState,
    recommendState: RecommendWineUiState,
    mainWineState: MainWineUiState,
    blurBitmap: Bitmap?
) {
    val scrollState = rememberScrollState()
    val context = LocalContext.current

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
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(372.dp)
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
                        onRefreshButtonClick(context)
                    }
                )
//                if (blurBitmap != null) {
//                    RecommendWineCardWithRenderScript(
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .height(367.dp)
//                            .padding(start = 24.dp, end = 24.dp, top = 19.dp)
//                            .clip(RoundedCornerShape(16.dp)),
//                        recommendWine = recommendState.recommendWine,
//                        onRefreshButtonClick = {
//                            homeViewModel.getRecommendWine(context)
//                        },
//                        blurImage = blurBitmap
//                    )
//                }
            }
            is RecommendWineUiState.Loading -> {
                RecommendImage(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(367.dp)
                        .padding(start = 24.dp, end = 24.dp, top = 19.dp),
                    onButtonClick = {
                        onRefreshButtonClick(context)
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
    currentRoute: NavDestination?,
    onBottomTabsClick: (String) -> Unit,
    bottomNavigationItems: List<String>
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
                    when (screen) {
                        "home_screen.screen" -> {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_home_variant),
                                contentDescription = null
                            )
                        }
                        "user_screen.screen" -> {
                            Icon(
                                imageVector = Icons.Filled.AccountCircle,
                                contentDescription = null
                            )
                        }
                    }
                },
                selected = currentRoute?.hierarchy?.any { it.route == screen } == true, // 선택에 따라서 색상이 변경됩니다.
                selectedContentColor = ProofTheme.color.primary50,
                alwaysShowLabel = false,
                onClick = {
                    onBottomTabsClick(screen)
                }
            )
        }
    }
}

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
            backgroundColor = ProofTheme.color.primary300,
            textColor = ProofTheme.color.white,
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
            HomeScreen(
                modifier = Modifier.background(color = ProofTheme.color.black),
                onCategoryClick = {},
                onWineBoardClick = {},
                onWorldCupItemClick = {},
                onRefreshButtonClick = {},
                bestWorldCupState = BestWorldCupUiState.Loading,
                mainWineState = MainWineUiState.Loading,
                recommendState = RecommendWineUiState.Loading,
                blurBitmap = null
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
            bottomNavigationItems = listOf("")
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
