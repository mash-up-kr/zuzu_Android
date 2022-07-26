package com.mashup.zuzu.ui.category

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.debugInspectorInfo
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import com.mashup.zuzu.R
import com.mashup.zuzu.data.model.Wine
import com.mashup.zuzu.data.model.wines
import com.mashup.zuzu.ui.component.*
import com.mashup.zuzu.ui.model.Category
import com.mashup.zuzu.ui.model.categoryList
import com.mashup.zuzu.ui.theme.Black
import com.mashup.zuzu.ui.theme.ProofTheme
import kotlin.math.absoluteValue

/**
 * @Created by 김현국 2022/07/03
 * @Time 5:51 오후
 */

@Composable
fun CategoryRoute(
    category: String,
    categoryViewModel: CategoryViewModel = hiltViewModel(),
    onWineBoardClick: (Wine) -> Unit,
    onBackButtonClick: () -> Unit,
) {

    val index = categoryList.withIndex().filter { it.value.title == category }.map { it.index }[0]
    val wineState by categoryViewModel.wineList.collectAsState()

    CategoryScreen(
        modifier = Modifier.fillMaxHeight().fillMaxWidth().background(color = Black),
        index = index,
        wineState = wineState,
        onWineBoardClick = onWineBoardClick,
        onBackButtonClick = onBackButtonClick,
        getWineList = { category ->
            categoryViewModel.getWineList(category = category)
        }
    )
}

@Composable
fun CategoryScreen(
    modifier: Modifier,
    index: Int,
    onWineBoardClick: (Wine) -> Unit,
    onBackButtonClick: () -> Unit,
    getWineList: (String) -> Unit,
    wineState: CategoryUiState
) {
    val categoryState = rememberCategoryState(index)

    Column(
        modifier = modifier
    ) {
        Icon(
            modifier = Modifier
                .padding(top = 30.dp, bottom = 24.dp, start = 24.dp).clickable {
                    onBackButtonClick()
                },
            imageVector = Icons.Outlined.ArrowBack,
            tint = ProofTheme.color.white,
            contentDescription = null
        )

        // 상단 카테고리 선택바
        CustomScrollableTabRow(
            tabs = categoryList,
            selectedTabIndex = categoryState.selectedTabIndex
        ) { tabIndex ->
            categoryState.updateSelectedTabIndex(tabIndex)
            // 여기서 아이템 갱신
            getWineList(categoryList[tabIndex].title)
        }

        Row(
            modifier = Modifier.align(Alignment.End).padding(end = 25.dp, top = 15.dp)
        ) {
            // viewMode grid or horizontal scroll pager
            Row() {
                if (categoryState.viewMode) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_pager),
                        contentDescription = null,
                        modifier = Modifier.clickable {
                            categoryState.changeViewMode()
                        }
                    )
                    Spacer(modifier = Modifier.width(21.dp).wrapContentHeight())
                    Image(
                        painter = painterResource(id = R.drawable.ic_grid_selected),
                        contentDescription = null,
                        modifier = Modifier.clickable(enabled = false) {}

                    )
                } else {
                    Image(
                        painter = painterResource(id = R.drawable.ic_pager_selected),
                        contentDescription = null,
                        modifier = Modifier.clickable(enabled = false) {}
                    )
                    Spacer(modifier = Modifier.width(21.dp).wrapContentHeight())
                    Image(
                        painter = painterResource(id = R.drawable.ic_grid),
                        contentDescription = null,
                        modifier = Modifier.clickable {
                            categoryState.changeViewMode()
                        },
                    )
                }
            }
        }

        // 하단 wine items
        when (wineState) {
            is CategoryUiState.Loading -> {
                // some indicator or loading screen
            }
            is CategoryUiState.Success -> {
                if (categoryState.viewMode)
                    CategoryWineItems(
                        wines = wineState.wineList, onWineBoardClick = onWineBoardClick,
                        modifier = Modifier
                            .padding(top = 28.dp)
                            .fillMaxWidth()
                    )
                else {
                    HorizontalPagerWithOffsetTransition(
                        modifier = Modifier.wrapContentHeight().fillMaxWidth()
                            .padding(top = 28.dp),
                        onWineBoardClick = onWineBoardClick,
                        wines = wineState.wineList
                    )
                    Box(
                        modifier = Modifier.fillMaxHeight().fillMaxWidth().padding(top = 16.dp)
                    ) {
                        RoundedButton(
                            modifier = Modifier.align(Center)
                                .height(41.dp).width(148.dp),
                            onButtonClick = { /*TODO*/ }, fontSize = 13.sp, text = "+ 내 술 저장고에 추가"
                        )
                    }
                }
            }
            is CategoryUiState.Error -> {
                // some toast message ?
            }
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun HorizontalPagerWithOffsetTransition(
    modifier: Modifier,
    onWineBoardClick: (Wine) -> Unit,
    wines: List<Wine>
) {
    HorizontalPager(
        count = wines.size,
        // Add 32.dp horizontal padding to 'center' the pages
        contentPadding = PaddingValues(horizontal = 50.dp), // 양옆 패팅
        modifier = modifier.fillMaxWidth()
    ) { page ->

        PagerWineCard(
            modifier = Modifier
                .graphicsLayer {
                    // Calculate the absolute offset for the current page from the
                    // scroll position. We use the absolute value which allows us to mirror
                    // any effects for both directions
                    val pageOffset = calculateCurrentOffsetForPage(page).absoluteValue

                    // We animate the scaleX + scaleY, between 85% and 100% // 가운데에 오면 아이템이 커짐
                    lerp(
                        start = 0.85f,
                        stop = 1f,
                        fraction = 1f - pageOffset.coerceIn(0f, 1f)
                    ).also { scale ->
                        scaleX = scale
                        scaleY = scale
                    }

                    // We animate the alpha, between 50% and 100%
                    alpha = lerp(
                        start = 0.5f,
                        stop = 1f,
                        fraction = 1f - pageOffset.coerceIn(0f, 1f)
                    )
                }
//                // We add an offset lambda, to apply a light parallax effect // 옆에 자를 수 있는 코드
//                .offset {
//                    // Calculate the offset for the current page from the
//                    // scroll position
//                    val pageOffset =
//                        this@HorizontalPager.calculateCurrentOffsetForPage(page)
//                    // Then use it as a multiplier to apply an offset
//                    IntOffset(
//                        x = (36.dp * pageOffset).roundToPx(),
//                        y = 0
//                    )
//                }
                .width(282.dp).height(448.dp),
            wine = wines[page],
            onWineBoardClick = { onWineBoardClick(it) }
        )
    }
}

@Composable
fun CustomScrollableTabRow(
    tabs: List<Category>,
    selectedTabIndex: Int,
    onTabClick: (Int) -> Unit
) {
    val density = LocalDensity.current
    val tabWidths = remember {
        val tabWidthStateList = mutableStateListOf<Dp>()
        repeat(tabs.size) {
            tabWidthStateList.add(0.dp)
        }
        tabWidthStateList
    }
    ScrollableTabRow(
        backgroundColor = ProofTheme.color.black,
        selectedTabIndex = selectedTabIndex,
        contentColor = ProofTheme.color.white,
        edgePadding = 0.dp,
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                modifier = Modifier.customTabIndicatorOffset(
                    currentTabPosition = tabPositions[selectedTabIndex],
                    tabWidth = tabWidths[selectedTabIndex]
                ),
                color = ProofTheme.color.primary200
            )
        }
    ) {
        tabs.forEachIndexed { tabIndex, tab ->
            Tab(
                selected = selectedTabIndex == tabIndex,
                onClick = { onTabClick(tabIndex) },
                text = {
                    if (selectedTabIndex == tabIndex) {
                        Text(
                            text = tab.title,
                            color = ProofTheme.color.primary200,
                            onTextLayout = { textLayoutResult ->
                                tabWidths[tabIndex] =
                                    with(density) { textLayoutResult.size.width.toDp() }
                            }
                        )
                    } else {
                        Text(
                            text = tab.title,
                            onTextLayout = { textLayoutResult ->
                                tabWidths[tabIndex] =
                                    with(density) { textLayoutResult.size.width.toDp() }
                            }
                        )
                    }
                }
            )
        }
    }
}

fun Modifier.customTabIndicatorOffset(
    currentTabPosition: TabPosition,
    tabWidth: Dp
): Modifier = composed(
    inspectorInfo = debugInspectorInfo {
        name = "customTabIndicatorOffset"
        value = currentTabPosition
    }
) {
    val currentTabWidth by animateDpAsState(
        targetValue = tabWidth,
        animationSpec = tween(durationMillis = 250, easing = FastOutSlowInEasing)
    )
    val indicatorOffset by animateDpAsState(
        targetValue = ((currentTabPosition.left + currentTabPosition.right - tabWidth) / 2),
        animationSpec = tween(durationMillis = 250, easing = FastOutSlowInEasing)
    )
    fillMaxWidth()
        .wrapContentSize(Alignment.BottomStart)
        .offset(x = indicatorOffset)
        .width(currentTabWidth)
}

@Composable
fun CategoryWineItems(
    wines: List<Wine>,
    modifier: Modifier,
    onWineBoardClick: (Wine) -> Unit
) {
    LazyVerticalGrid(
        modifier = modifier.padding(start = 20.dp, end = 20.dp),
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(32.dp),
        horizontalArrangement = Arrangement.spacedBy(20.dp),
    ) {
        items(wines) { wine ->
            Box(
                contentAlignment = Center
            ) {
                WineCardInHome(
                    modifier = Modifier.fillMaxWidth(),
                    height = 210.dp,
                    wine = wine, onWineBoardClick = onWineBoardClick
                )
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun PreviewCategoryScreen() {
    ProofTheme() {
        CategoryScreen(
            modifier = Modifier.fillMaxHeight().fillMaxWidth().background(color = ProofTheme.color.black),
            index = 0,
            onBackButtonClick = {},
            onWineBoardClick = {},
            getWineList = {},
            wineState = CategoryUiState.Loading
        )
    }
}

@Preview(showBackground = false)
@Composable
fun PreviewPagerCard() {
    ProofTheme() {
        HorizontalPagerWithOffsetTransition(
            onWineBoardClick = {},
            modifier = Modifier.fillMaxWidth().fillMaxHeight(),
            wines = wines
        )
    }
}
