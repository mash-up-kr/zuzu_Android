package com.mashup.zuzu.ui.home

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mashup.zuzu.R
import com.mashup.zuzu.data.model.Wine
import com.mashup.zuzu.data.model.wines
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
            modifier = Modifier.clickable { onSuggestionClick() }
                .align(Alignment.Bottom).width(36.dp).height(36.dp)
        )
    }
}

@Composable
fun HomeMainTitleItems(
    modifier: Modifier,
    onWineBoardClick: (Wine) -> Unit
) {
    LazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        itemsIndexed(wines) { index, wine ->
            WineCardInHome(modifier = Modifier.height(260.dp).width(220.dp), wine = wines[index], onWineBoardClick = onWineBoardClick)
        }
    }
}

@Composable
fun HomeSubTitle(
    modifier: Modifier,
    boldTitle: String,
    hintTitle: String
) {
    Column(modifier = modifier) {
        Text(
            text = boldTitle,
            fontSize = 18.sp,
            fontWeight = FontWeight.W700,
            color = ProofTheme.color.white
        )
        Text(
            text = hintTitle,
            fontWeight = FontWeight.W500,
            fontSize = 14.sp,
            color = ProofTheme.color.gray300
        )
    }
}

@Composable
fun HomeBestWorldCup(
    modifier: Modifier,
    bestWorldCupList: List<BestWorldCup>,
    onWorldCupItemClick: (BestWorldCup) -> Unit
) {
    LazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(bestWorldCupList) { bestWorldCup ->
            WorldCupCard(bestWorldCup, onWorldCupItemClick = onWorldCupItemClick)
        }
    }
}
@Composable
fun ZuzuHomeScreen(
    modifier: Modifier,
    onCategoryClick: (Category) -> Unit,
    onWorldCupItemClick: (BestWorldCup) -> Unit,
    onWineBoardClick: (Wine) -> Unit
) {
    val scrollState = rememberScrollState()
    Column(
        modifier = modifier.verticalScroll(scrollState)
    ) {
        HomeLogo(modifier = Modifier.padding(top = 24.dp, start = 24.dp))
        HomeMainTitle(modifier = Modifier.padding(top = 31.dp, start = 24.dp), {})
        Spacer(modifier = Modifier.fillMaxWidth().height(24.dp))
        HomeMainTitleItems(
            modifier = Modifier.padding(start = 24.dp),
            onWineBoardClick = onWineBoardClick
        )
        Spacer(modifier = Modifier.fillMaxWidth().height(32.dp))
        ColorSpacer(modifier = Modifier.fillMaxWidth().height(6.dp), color = ProofTheme.color.black)
        HomeSubTitle(
            modifier = Modifier.padding(start = 24.dp, top = 32.dp),
            boldTitle = "무엇을 마실지 고민이라면?",
            hintTitle = "주종별로 원하는 술을 탐색해보세요!"
        )
        CategoryItems(
            modifier = Modifier.fillMaxWidth().padding(start = 24.dp, top = 24.dp),
            onCategoryClick = onCategoryClick
        )
        Spacer(modifier = Modifier.fillMaxWidth().height(32.dp))
        ColorSpacer(modifier = Modifier.fillMaxWidth().height(6.dp), color = ProofTheme.color.black)
        HomeSubTitle(
            modifier = Modifier.padding(start = 24.dp, top = 32.dp),
            boldTitle = "지금 가장 인기있는 술드컵",
            hintTitle = "지금 바로 확인해보세요!"
        )
        HomeBestWorldCup(
            modifier = Modifier.padding(top = 24.dp, start = 24.dp, bottom = 56.dp), bestWorldCupList = bestWorldCupList,
            onWorldCupItemClick = onWorldCupItemClick
        )
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
                alwaysShowLabel = false,
                onClick = {
                    onBottomTabsClick(screen.route)
                }
            )
        }
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
            ZuzuHomeScreen(modifier = Modifier.background(color = ProofTheme.color.black), {}, {}, {})
        }
    }
}

@Preview
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
            boldTitle = "무엇을 마실지 고민이라면?",
            hintTitle = "주종별로 원하는 술을 탐색해보세요!"
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
