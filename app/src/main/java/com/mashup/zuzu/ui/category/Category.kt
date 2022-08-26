package com.mashup.zuzu.ui.category

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.mashup.zuzu.R
import com.mashup.zuzu.compose.component.*
import com.mashup.zuzu.compose.component.TabPosition
import com.mashup.zuzu.compose.theme.ProofTheme
import com.mashup.zuzu.data.model.Category
import com.mashup.zuzu.data.model.Wine

/**
 * @Created by 김현국 2022/07/03
 */

@Composable
fun CategoryRoute(
    viewModel: CategoryViewModel,
    index: Int,
    categoryList: List<Category>,
    onClick: (CategoryUiEvents) -> Unit
) {
    val wineListState by viewModel.wineListState.collectAsState()
    val page by viewModel.page
    val pageSize = viewModel.PAGE_SIZE
    CategoryScreen(
        index = index,
        categoryList = categoryList,
        wineListState = wineListState,
        onClick = onClick,
        onLoadNextPage = { viewModel.getWineListWithPageAndCategoryNextPage() },
        onScrollPositionChange = { position ->
            viewModel.onChangeWineListScrollPosition(position = position)
        },
        page = page,
        pageSize = pageSize
    )
}

@Composable
fun CategoryScreen(
    modifier: Modifier = Modifier,
    index: Int,
    categoryList: List<Category>,
    wineListState: WineListWithPageAndCategoryUiState,
    onScrollPositionChange: (Int) -> Unit,
    onLoadNextPage: () -> Unit,
    page: Int,
    pageSize: Int,
    onClick: (CategoryUiEvents) -> Unit
) {
    val categoryState = rememberCategoryState(index)

    Column(
        modifier = modifier
            .fillMaxHeight()
            .fillMaxWidth()
    ) {
        Icon(
            modifier = Modifier
                .padding(top = 30.dp, bottom = 24.dp, start = 24.dp).clickable {
                    onClick(CategoryUiEvents.BackButtonClick)
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
            onClick(CategoryUiEvents.TabClick(categoryList[tabIndex].title))
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
                        }
                    )
                }
            }
        }

        when (wineListState) {
            is WineListWithPageAndCategoryUiState.Success -> {
                if (categoryState.viewMode) {
                    CategoryWineItems(
                        wines = wineListState.wineList,
                        onWineBoardClick = { wine ->
                            onClick(CategoryUiEvents.WineBoardClick(wine = wine))
                        },
                        modifier = Modifier
                            .padding(top = 28.dp)
                            .fillMaxWidth(),
                        onScrollPositionChange = onScrollPositionChange,
                        onLoadNextPage = onLoadNextPage,
                        page = page,
                        pageSize = pageSize
                    )
                } else {
                    HorizontalPagerWithOffsetTransitionWithPage(
                        modifier = Modifier.wrapContentHeight().fillMaxWidth()
                            .padding(top = 28.dp),
                        onWineBoardClick = { wine ->
                            onClick(CategoryUiEvents.WineBoardClick(wine = wine))
                        },
                        wineList = wineListState.wineList,
                        null,
                        onLoadData = onLoadNextPage,
                        onScrollPositionChange = onScrollPositionChange,
                        pageSize = pageSize,
                        currentPage = page
                    )
                }
            }
            is WineListWithPageAndCategoryUiState.Loading -> {
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
        }
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
    onWineBoardClick: (Wine) -> Unit,
    onScrollPositionChange: (Int) -> Unit,
    onLoadNextPage: () -> Unit,
    page: Int,
    pageSize: Int
) {
    LazyVerticalGrid(
        modifier = modifier.padding(start = 20.dp, end = 20.dp),
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(25.dp),
        horizontalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        itemsIndexed(wines, key = { index: Int, item: Wine -> item.id }) { index, wine ->
            Box(
                contentAlignment = Center
            ) {
                onScrollPositionChange(index)

                if (index + 1 >= page * pageSize) {
                    onLoadNextPage()
                }

                WineCardInHome(
                    modifier = Modifier.fillMaxWidth().height(340.dp),
                    height = 210.dp,
                    wine = wine,
                    onWineBoardClick = onWineBoardClick
                )
            }
        }
    }
}
