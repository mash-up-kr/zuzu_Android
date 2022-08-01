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
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.debugInspectorInfo
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mashup.zuzu.R
import com.mashup.zuzu.data.model.Category
import com.mashup.zuzu.data.model.Wine
import com.mashup.zuzu.data.model.categoryList
import com.mashup.zuzu.data.model.wines
import com.mashup.zuzu.ui.component.*
import com.mashup.zuzu.ui.component.TabPosition
import com.mashup.zuzu.ui.theme.Black
import com.mashup.zuzu.ui.theme.ProofTheme

/**
 * @Created by 김현국 2022/07/03
 * @Time 5:51 오후
 */

@Composable
fun CategoryRoute(
    category: String,
    categoryList: List<Category>,
    categoryViewModel: CategoryViewModel = hiltViewModel(),
    onWineBoardClick: (Wine) -> Unit,
    onBackButtonClick: () -> Unit,
) {

    val index = categoryList.withIndex().filter { it.value.title == category }.map { it.index }[0]
    val wineState by categoryViewModel.wineList.collectAsState()

    CategoryScreen(
        modifier = Modifier.fillMaxHeight().fillMaxWidth().background(color = Black),
        index = index,
        categoryList = categoryList,
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
    categoryList: List<Category>,
    onWineBoardClick: (Wine) -> Unit,
    onBackButtonClick: () -> Unit,
    getWineList: (String) -> Unit,
    wineState: WineListWithCategoryUiState
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
            painter = painterResource(id = R.drawable.ic_arrow_left),
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
            is WineListWithCategoryUiState.Loading -> {
                // some indicator or loading screen
            }
            is WineListWithCategoryUiState.Success -> {
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
                }
            }
            is WineListWithCategoryUiState.Error -> {
                // some toast message ?
            }
        }
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
    CustomScrollableTabRow(
        modifier = Modifier.fillMaxWidth(),
        backgroundColor = ProofTheme.color.black,
        selectedTabIndex = selectedTabIndex,
        contentColor = ProofTheme.color.gray100,
        edgePadding = 0.dp,
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                modifier = Modifier.customTabIndicatorOffset(
                    currentTabPosition = tabPositions[selectedTabIndex],
                    tabWidth = tabWidths[selectedTabIndex]
                ),
                color = ProofTheme.color.primary200
            )
        },
    ) {
        tabs.forEachIndexed { tabIndex, tab ->
            val selected = selectedTabIndex == tabIndex
            Tab(
                selected = selected,
                onClick = { onTabClick(tabIndex) },
                text = {
                    Text(
                        maxLines = 1,
                        text = tab.title,
                        style = if (selected) ProofTheme.typography.headingXS else ProofTheme.typography.bodyM,
                        textAlign = TextAlign.Center,
                        onTextLayout = { textLayoutResult ->
                            tabWidths[tabIndex] = with(density) { textLayoutResult.size.width.toDp() }
                        }
                    )
                },
                selectedContentColor = ProofTheme.color.primary200,
                unselectedContentColor = ProofTheme.color.gray100
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
            wineState = WineListWithCategoryUiState.Loading,
            categoryList = categoryList
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
