package com.mashup.zuzu.ui.home

import android.graphics.Bitmap
import android.os.Build
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.placeholder
import com.google.accompanist.placeholder.shimmer
import com.mashup.zuzu.R
import com.mashup.zuzu.compose.component.*
import com.mashup.zuzu.compose.theme.ProofTheme
import com.mashup.zuzu.data.model.Wine
import com.mashup.zuzu.data.model.categoryList
import com.mashup.zuzu.data.model.dummy.dummyCategoryList
import com.mashup.zuzu.data.model.dummy.dummyWineList
import com.mashup.zuzu.data.model.dummy.dummyWorldCupList
import com.mashup.zuzu.ui.model.BestWorldCup
import kotlin.math.roundToInt

/**
 * @Created by 김현국 2022/06/30
 */

@Composable
fun HomeRoute(
    viewModel: HomeViewModel,
    onClick: (HomeUiEvents) -> Unit
) {
    val bestWorldCupState by viewModel.bestWorldCupList.collectAsState()
    val recommendState by viewModel.recommendWine.collectAsState()
    val mainWineState by viewModel.mainWineList.collectAsState()
    val blurBitmap by viewModel.bitmap.collectAsState()
    val categoryListState by viewModel.categoryList.collectAsState()
    val networkState by viewModel.connection.collectAsState()

    LaunchedEffect(networkState) {
        if (networkState) {
            viewModel.initData()
        }
    }

    HomeScreen(
        bestWorldCupState = bestWorldCupState,
        recommendState = recommendState,
        mainWineState = mainWineState,
        blurBitmap = blurBitmap,
        categoryListState = categoryListState,
        onClick = onClick
    )
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    bestWorldCupState: BestWorldCupUiState,
    recommendState: RecommendWineUiState,
    mainWineState: MainWineUiState,
    blurBitmap: Bitmap?,
    categoryListState: CategoryListUiState,
    onClick: (HomeUiEvents) -> Unit
) {
    val scrollState = rememberScrollState()
    val context = LocalContext.current

    Column(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .verticalScroll(scrollState)
    ) {
        HomeLogo(
            modifier = Modifier
                .padding(top = 24.dp, start = 24.dp)
                .width(80.dp)
                .height(32.dp)
        )
        HomeMainTitle(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 31.dp)
        )
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(22.dp)
        )
        when (mainWineState) {
            is MainWineUiState.Success -> {
                val pagerState = rememberPagerState((mainWineState.mainWines.size / 2).toDouble().roundToInt())

                HorizontalPagerWithOffsetTransition(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(415.dp),
                    onWineBoardClick = { wine ->
                        onClick(HomeUiEvents.WineBoardClick(wine))
                    },
                    wines = mainWineState.mainWines,
                    pagerState = pagerState,
                    childModifier = null
                )
            }
            is MainWineUiState.Error -> {}
            is MainWineUiState.Loading -> {
                val pagerState = rememberPagerState((dummyWineList.size / 2).toDouble().roundToInt())
                HorizontalPagerWithOffsetTransition(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(415.dp),
                    onWineBoardClick = {},
                    wines = dummyWineList,
                    pagerState = pagerState,
                    childModifier = Modifier.placeholder(
                        visible = true,
                        color = ProofTheme.color.gray600,
                        highlight = PlaceholderHighlight.shimmer(
                            highlightColor = ProofTheme.color.gray500
                        )
                    )
                )
            }
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
            color = ProofTheme.color.gray600
        )
        HomeSubTitle(
            modifier = Modifier.padding(start = 24.dp, top = 32.dp),
            boldTitle = stringResource(id = R.string.home_sub_title)
        )
        when (categoryListState) {
            is CategoryListUiState.Success -> {
                CategoryItems(
                    modifier = Modifier
                        .padding(start = 24.dp, top = 24.dp),
                    categoryList = categoryListState.categoryList,
                    onCategoryClick = { category ->
                        onClick(
                            HomeUiEvents.CategoryClick(
                                categoryList = categoryListState.categoryList,
                                category = category
                            )
                        )
                    },
                    childModifier = null
                )
            }
            is CategoryListUiState.Loading -> {
                CategoryItems(
                    modifier = Modifier.padding(start = 24.dp, top = 24.dp),
                    categoryList = dummyCategoryList,
                    onCategoryClick = {},
                    childModifier = Modifier.placeholder(
                        visible = true,
                        color = ProofTheme.color.gray600,
                        highlight = PlaceholderHighlight.shimmer(
                            highlightColor = ProofTheme.color.gray500
                        )
                    )
                )
            }
            is CategoryListUiState.Error -> {
            }
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
            color = ProofTheme.color.gray600
        )
        HomeSubTitle(
            modifier = Modifier.padding(start = 24.dp, top = 40.dp),
            boldTitle = stringResource(id = R.string.home_today_random_wine)
        )
        when (recommendState) {
            is RecommendWineUiState.Success -> {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                    RecommendWineCard(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(367.dp)
                            .padding(start = 24.dp, end = 24.dp, top = 19.dp)
                            .clip(RoundedCornerShape(16.dp)),
                        recommendWine = recommendState.recommendWine,
                        onRefreshButtonClick = {
                            onClick(HomeUiEvents.RefreshButtonClick(context = context))
                        }
                    )
                } else {
                    if (blurBitmap != null) {
                        RecommendWineCardWithRenderScript(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(367.dp)
                                .padding(start = 24.dp, end = 24.dp, top = 19.dp)
                                .clip(RoundedCornerShape(16.dp)),
                            recommendWine = recommendState.recommendWine,
                            onRefreshButtonClick = {
                                onClick(HomeUiEvents.RefreshButtonClick(context = context))
                            },
                            blurImage = blurBitmap
                        )
                    }
                }
            }
            is RecommendWineUiState.Loading -> {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(367.dp)
                        .padding(start = 24.dp, end = 24.dp, top = 19.dp)
                        .clip(shape = RoundedCornerShape(16.dp))
                        .placeholder(
                            visible = true,
                            color = ProofTheme.color.gray600,
                            highlight = PlaceholderHighlight.shimmer(
                                highlightColor = ProofTheme.color.gray500
                            )
                        )
                )
            }
            is RecommendWineUiState.Init -> {
                RecommendImage(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(367.dp)
                        .padding(start = 24.dp, end = 24.dp, top = 19.dp)
                        .clip(shape = RoundedCornerShape(16.dp)),
                    onButtonClick = {
                        onClick(HomeUiEvents.RefreshButtonClick(context = context))
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
            color = ProofTheme.color.gray600
        )
        HomeSubTitle(
            modifier = Modifier.padding(start = 24.dp, top = 32.dp),
            boldTitle = stringResource(id = R.string.home_today_best_worldcup)
        )
        when (bestWorldCupState) {
            is BestWorldCupUiState.Loading -> {
                HomeBestWorldCup(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .padding(top = 24.dp, start = 24.dp, end = 24.dp),
                    bestWorldCupList = dummyWorldCupList,
                    onWorldCupItemClick = { bestWorldCup ->
                        onClick(HomeUiEvents.WorldCupItemClick(bestWorldCup = bestWorldCup))
                    },
                    childModifier = Modifier.placeholder(
                        visible = true,
                        color = ProofTheme.color.gray600,
                        highlight = PlaceholderHighlight.shimmer(
                            highlightColor = ProofTheme.color.gray500
                        )
                    )
                )
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
                    onWorldCupItemClick = { bestWorldCup ->
                        onClick(HomeUiEvents.WorldCupItemClick(bestWorldCup = bestWorldCup))
                    },
                    childModifier = null
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
            .height(52.dp),
        backgroundColor = ProofTheme.color.black
    ) {
        bottomNavigationItems.forEach { screen ->
            BottomNavigationItem(
                icon = {
                    when (screen) {
                        "home_screen.screen" -> {
                            Icon(
                                modifier = Modifier.offset(x = 20.dp),
                                painter = painterResource(id = R.drawable.ic_home_variant),
                                contentDescription = null
                            )
                        }
                        "user_screen.screen" -> {
                            Icon(
                                modifier = Modifier.offset(x = (-20).dp),
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
    modifier: Modifier
) {
    Box(
        modifier = modifier
    ) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            style = ProofTheme.typography.headingXL,
            color = ProofTheme.color.white,
            text = stringResource(id = R.string.home_top_title),
            textAlign = TextAlign.Center
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
                wine = wines[index],
                onWineBoardClick = onWineBoardClick
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
    onWorldCupItemClick: (BestWorldCup) -> Unit,
    childModifier: Modifier?
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(32.dp)
    ) {
        bestWorldCupList.forEach { bestWorldCup ->
            WorldCupCard(
                modifier = Modifier.fillMaxWidth(),
                worldCupItem = bestWorldCup,
                onWorldCupItemClick = onWorldCupItemClick,
                childModifier = childModifier
            )
        }
    }
}

@Composable
fun RecommendImage(
    modifier: Modifier,
    onButtonClick: () -> Unit
) {
    Box(modifier = modifier) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            painter = painterResource(id = R.drawable.img_recommend_wine),
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
            text = stringResource(id = R.string.home_see_random_drink),
            textStyle = ProofTheme.typography.buttonM,
            onButtonClick = onButtonClick
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
                bestWorldCupState = BestWorldCupUiState.Loading,
                mainWineState = MainWineUiState.Loading,
                recommendState = RecommendWineUiState.Loading,
                blurBitmap = null,
                categoryListState = CategoryListUiState.Loading,
                onClick = {}
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
        CategoryItems(
            modifier = Modifier.fillMaxWidth(),
            categoryList = categoryList,
            onCategoryClick = { category ->
            },
            null
        )
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
