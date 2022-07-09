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
import androidx.compose.material.icons.outlined.ExpandMore
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color.Companion.White
import com.mashup.zuzu.ui.theme.ProofTheme
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.debugInspectorInfo
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.zuzu_android.R
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import com.mashup.zuzu.data.model.Wine
import com.mashup.zuzu.data.model.wines
import com.mashup.zuzu.ui.component.*
import kotlin.math.absoluteValue

/**
 * @Created by 김현국 2022/07/03
 * @Time 5:51 오후
 */

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CategoryScreen(
    modifier: Modifier,
    category: String,
    onWineBoardClick: (Wine) -> Unit,
    onBackButtonClick: () -> Unit,
    categoryViewModel: CategoryViewModel = viewModel(
        factory = CategoryViewModelFactory(category = category)
    )
) {
    val index = categoryList.withIndex().filter { it.value.title == category }.map { it.index }
    val categoryState = rememberCategoryState(index[0])
    val wineState = categoryViewModel.wineList.collectAsState().value
    with(categoryState) {
        Column(
            modifier = modifier
        ) {

            Icon(
                modifier = Modifier
                    .padding(top = 30.dp, bottom = 24.dp, start = 24.dp).clickable {
                        onBackButtonClick()
                    },
                imageVector = Icons.Outlined.ArrowBack,
                tint = White,
                contentDescription = null
            )

            // 상단 카테고리 선택바
            CustomScrollableTabRow(
                tabs = categoryList,
                selectedTabIndex = selectedTabIndex
            ) { tabIndex ->
                updateSelectedTabIndex(tabIndex)
                // 여기서 아이템 갱신
                categoryViewModel.getWineList(categoryList[tabIndex].title)
            }

            Row(
                modifier = Modifier.fillMaxWidth().padding(start = 24.dp, top = 22.dp, bottom = 31.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // 정렬 기준 최신 ,오래된순 dropdownmenu
                ExposedDropdownMenuBox(
                    expanded = expanded, onExpandedChange = {
                        updateExpanded()
                    }
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            modifier = Modifier.wrapContentWidth()
                                .height(21.dp),
                            text = sortMenuItemText,
                            maxLines = 1,
                            color = White,
                            fontSize = 13.sp,

                        )
                        Icon(
                            imageVector = Icons.Outlined.ExpandMore,
                            contentDescription = null,
                            modifier = Modifier.width(16.dp).height(21.dp),
                            tint = White
                        )
                    }

                    ExposedDropdownMenu(
                        modifier = Modifier.width(120.dp),
                        expanded = expanded,
                        onDismissRequest = {
                            updateExpanded()
                        }
                    ) {
                        sortMenuItem.forEach { selectedSortMenu ->
                            DropdownMenuItem(onClick = {
                                updateSortMenuItemText(selectedSortMenu)
                                updateExpanded()
                            }) {
                                Text(
                                    text = selectedSortMenu,
                                    fontSize = 13.sp
                                )
                            }
                        }
                    }
                }
                // viewMode grid or horizontal scroll pager
                Row(
                    modifier = Modifier.padding(end = 26.5.dp)
                ) {
                    if (viewMode) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_pager),
                            contentDescription = null,
                            modifier = Modifier.clickable {
                                changeViewMode()
                            }
                        )
                        Spacer(modifier = Modifier.width(15.dp).wrapContentHeight())
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
                        Spacer(modifier = Modifier.width(15.dp).wrapContentHeight())
                        Image(
                            painter = painterResource(id = R.drawable.ic_grid),
                            contentDescription = null,
                            modifier = Modifier.clickable {
                                changeViewMode()
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
                    if (viewMode)
                        CategoryWineItems(
                            wines = wineState.wineList, onWineBoardClick = onWineBoardClick,
                            modifier = Modifier
                                .padding(top = 28.dp, start = 24.dp, end = 24.dp)
                                .fillMaxWidth()
                        )
                    else {
                        HorizontalPagerWithOffsetTransition(
                            modifier = Modifier.wrapContentHeight().fillMaxWidth(),
                            onWineBoardClick = onWineBoardClick,
                            wines = wineState.wineList
                        )
                        Box(
                            modifier= Modifier.fillMaxHeight().fillMaxWidth()
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
                // We add an offset lambda, to apply a light parallax effect
                .offset {
                    // Calculate the offset for the current page from the
                    // scroll position
                    val pageOffset =
                        this@HorizontalPager.calculateCurrentOffsetForPage(page)
                    // Then use it as a multiplier to apply an offset
                    IntOffset(
                        x = (36.dp * pageOffset).roundToPx(),
                        y = 0
                    )
                }
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
        backgroundColor = Black,
        selectedTabIndex = selectedTabIndex,
        contentColor = White,
        edgePadding = 0.dp,
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                modifier = Modifier.customTabIndicatorOffset(
                    currentTabPosition = tabPositions[selectedTabIndex],
                    tabWidth = tabWidths[selectedTabIndex]
                ),
                color = Purple200
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
                            color = Purple200,
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
        columns = GridCells.Fixed(2),
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(30.dp)
    ) {
        items(wines) { wine ->
            Box(
                contentAlignment = Center
            ) {
                WineBoardCard(
                    modifier = Modifier
                        .height(166.dp)
                        .width(130.dp),
                    wine = wine, onWineBoardClick = onWineBoardClick
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCategoryScreen() {
    ZuzuAndroidTheme() {
        CategoryScreen(
            modifier = Modifier.fillMaxHeight().fillMaxWidth().background(color = Black),
            category = "전부",
            onBackButtonClick = {},
            onWineBoardClick = {},
            categoryViewModel = CategoryViewModel("전부")
        ) //
    }
}

@Preview(showBackground = false)
@Composable
fun PreviewPagerCard() {
    ZuzuAndroidTheme() {
        HorizontalPagerWithOffsetTransition(
            onWineBoardClick = {},
            modifier = Modifier.fillMaxWidth().fillMaxHeight(),
            wines = wines
        )
//
    }
}
