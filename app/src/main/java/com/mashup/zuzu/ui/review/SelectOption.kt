package com.mashup.zuzu.ui.review

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.mashup.zuzu.R
import com.mashup.zuzu.compose.theme.ProofTheme
import dev.chrisbanes.snapper.ExperimentalSnapperApi
import dev.chrisbanes.snapper.rememberSnapperFlingBehavior

@Composable
fun WeatherSelectOption(
    navigateDateSelectPage: (String) -> Unit,
    modifier: Modifier
) {
    val optionContents = listOf(
        Pair(
            painterResource(id = R.drawable.ic_review_write_weather_rain),
            stringResource(R.string.weather_rain)
        ),
        Pair(
            painterResource(id = R.drawable.ic_review_write_weather_rain),
            stringResource(R.string.weather_snow)
        ),
        Pair(
            painterResource(id = R.drawable.ic_review_write_weather_rain),
            stringResource(R.string.weather_sunny)
        ),
        Pair(
            painterResource(id = R.drawable.ic_review_write_weather_rain),
            stringResource(R.string.weather_cloudy)
        )
    )

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalArrangement = Arrangement.spacedBy(14.dp),
        modifier = modifier.height(130.dp)
    ) {
        items(optionContents) { optionContent ->
            Button(
                onClick = {
                    navigateDateSelectPage(optionContent.second)
                },
                colors = ButtonDefaults.buttonColors(backgroundColor = ProofTheme.color.gray600),
                modifier = Modifier
                    .height(52.dp)
            ) {
                Image(
                    painter = optionContent.first,
                    contentDescription = "",
                    modifier = Modifier
                        .padding(end = 4.dp)
                        .width(24.dp)
                        .height(24.dp)
                )

                Text(
                    text = optionContent.second,
                    style = ProofTheme.typography.headingXS,
                    color = ProofTheme.color.white
                )
            }
        }
    }
}

@Composable
fun DateSelectOption(
    navigatePartnerPage: (String) -> Unit,
    modifier: Modifier
) {
    val optionContents = listOf(
        Pair(
            painterResource(id = R.drawable.ic_review_write_weather_rain),
            stringResource(R.string.date_dinner)
        ),
        Pair(
            painterResource(id = R.drawable.ic_review_write_weather_rain),
            stringResource(R.string.date_afternoon)
        ),
        Pair(
            painterResource(id = R.drawable.ic_review_write_weather_rain),
            stringResource(R.string.date_night)
        ),
        Pair(
            painterResource(id = R.drawable.ic_review_write_weather_rain),
            stringResource(R.string.date_dawn)
        )
    )

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalArrangement = Arrangement.spacedBy(14.dp),
        modifier = modifier.height(130.dp)
    ) {
        items(optionContents) { optionContent ->
            Button(
                onClick = {
                    navigatePartnerPage(optionContent.second)
                },
                colors = ButtonDefaults.buttonColors(backgroundColor = ProofTheme.color.gray600),
                modifier = Modifier
                    .height(52.dp)
            ) {
                Image(
                    painter = optionContent.first,
                    contentDescription = "",
                    modifier = Modifier
                        .padding(end = 4.dp)
                        .width(24.dp)
                        .height(24.dp)
                )

                Text(
                    text = optionContent.second,
                    style = ProofTheme.typography.headingXS,
                    color = ProofTheme.color.white
                )
            }
        }
    }
}

@Composable
fun PartnerSelectOption(
    navigateGroupPage: (String) -> Unit,
    navigateSoloPage: (String) -> Unit,
    modifier: Modifier
) {
    val optionContents = listOf(
        Pair(
            painterResource(id = R.drawable.ic_review_write_weather_rain),
            stringResource(R.string.partner_solo)
        ),
        Pair(
            painterResource(id = R.drawable.ic_review_write_weather_rain),
            stringResource(R.string.partner_friends)
        ),
        Pair(
            painterResource(id = R.drawable.ic_review_write_weather_rain),
            stringResource(R.string.partner_couple)
        ),
        Pair(
            painterResource(id = R.drawable.ic_review_write_weather_rain),
            stringResource(R.string.partner_party)
        )
    )

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalArrangement = Arrangement.spacedBy(14.dp),
        modifier = modifier.height(130.dp)
    ) {
        itemsIndexed(optionContents) { index, optionContent ->
            Button(
                onClick = {
                    when (index) {
                        0 -> {
                            navigateSoloPage(optionContent.second)
                        }

                        else -> {
                            navigateGroupPage(optionContent.second)
                        }
                    }
                },
                colors = ButtonDefaults.buttonColors(backgroundColor = ProofTheme.color.gray600),
                modifier = Modifier
                    .height(52.dp)
            ) {
                Image(
                    painter = optionContent.first,
                    contentDescription = "",
                    modifier = Modifier
                        .padding(end = 4.dp)
                        .width(24.dp)
                        .height(24.dp)
                )

                Text(
                    text = optionContent.second,
                    style = ProofTheme.typography.headingXS,
                    color = ProofTheme.color.white
                )
            }
        }
    }
}

@Composable
fun GroupSelectOption(
    navigateTastePage: (String) -> Unit,
    modifier: Modifier
) {
    val optionContents = listOf(
        Pair(
            painterResource(id = R.drawable.ic_review_write_weather_rain),
            stringResource(R.string.mood_friendly)
        ),
        Pair(
            painterResource(id = R.drawable.ic_review_write_weather_rain),
            stringResource(R.string.mood_serious)
        ),
        Pair(
            painterResource(id = R.drawable.ic_review_write_weather_rain),
            stringResource(R.string.mood_romantic)
        ),
        Pair(
            painterResource(id = R.drawable.ic_review_write_weather_rain),
            stringResource(R.string.mood_crazy)
        ),
        Pair(
            painterResource(id = R.drawable.ic_review_write_weather_rain),
            stringResource(R.string.mood_groomy)
        ),
        Pair(
            painterResource(id = R.drawable.ic_review_write_weather_rain),
            stringResource(R.string.mood_congratulations)
        )
    )

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalArrangement = Arrangement.spacedBy(14.dp),
        modifier = modifier.height(180.dp)
    ) {
        items(optionContents) { optionContent ->
            Button(
                onClick = {
                    navigateTastePage(optionContent.second)
                },
                colors = ButtonDefaults.buttonColors(backgroundColor = ProofTheme.color.gray600),
                modifier = Modifier
                    .height(52.dp)
            ) {
                Image(
                    painter = optionContent.first,
                    contentDescription = "",
                    modifier = Modifier
                        .padding(end = 4.dp)
                        .width(24.dp)
                        .height(24.dp)
                )

                Text(
                    text = optionContent.second,
                    style = ProofTheme.typography.headingXS,
                    color = ProofTheme.color.white
                )
            }
        }
    }

    Text(
        text = "혹시 몇 차였어요?",
        style = ProofTheme.typography.headingL,
        color = ProofTheme.color.white,
        modifier = Modifier.padding(top = 40.dp, bottom = 20.dp)
    )

    val secondOptionContents = listOf("1차", "2차", "3차 이상")

    Row(
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        secondOptionContents.forEach { content ->
            Button(
                onClick = {
                },
                colors = ButtonDefaults.buttonColors(backgroundColor = ProofTheme.color.gray600),
                modifier = Modifier
                    .weight(1f)
                    .height(52.dp)
            ) {
                Text(
                    text = content,
                    style = ProofTheme.typography.headingXS,
                    color = ProofTheme.color.white
                )
            }
        }
    }
}

@Composable
fun SoloSelectOption(
    navigateTastePage: (String) -> Unit,
    modifier: Modifier
) {
    val optionContents = listOf(
        Pair(
            painterResource(id = R.drawable.ic_review_write_weather_rain),
            stringResource(R.string.mood_happy)
        ),
        Pair(
            painterResource(id = R.drawable.ic_review_write_weather_rain),
            stringResource(R.string.mood_serious)
        ),
        Pair(
            painterResource(id = R.drawable.ic_review_write_weather_rain),
            stringResource(R.string.mood_groomy)
        ),
        Pair(
            painterResource(id = R.drawable.ic_review_write_weather_rain),
            stringResource(R.string.mood_crazy)
        ),
        Pair(
            painterResource(id = R.drawable.ic_review_write_weather_rain),
            stringResource(R.string.mood_taste)
        ),
        Pair(
            painterResource(id = R.drawable.ic_review_write_weather_rain),
            stringResource(R.string.mood_self_cong)
        )
    )

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalArrangement = Arrangement.spacedBy(14.dp),
        modifier = modifier.height(150.dp)
    ) {
        items(optionContents) { optionContent ->
            Button(
                onClick = {
                    navigateTastePage(optionContent.second)
                },
                colors = ButtonDefaults.buttonColors(backgroundColor = ProofTheme.color.gray600),
                modifier = Modifier
                    .height(52.dp)
            ) {
                Image(
                    painter = optionContent.first,
                    contentDescription = "",
                    modifier = Modifier
                        .padding(end = 4.dp)
                        .width(24.dp)
                        .height(24.dp)
                )

                Text(
                    text = optionContent.second,
                    style = ProofTheme.typography.headingXS,
                    color = ProofTheme.color.white
                )
            }
        }
    }
}

@OptIn(ExperimentalSnapperApi::class)
@Composable
fun TasteSelectOption(
    navigateSummaryPage: (List<Int>) -> Unit,
    modifier: Modifier
) {
    val lazyListState = rememberLazyListState()
    val layoutInfo = rememberSnapperFlingBehavior(lazyListState)

    val radioTitles = listOf(
        Pair("가벼워요", "무거워요"),
        Pair("달아요", "써요"),
        Pair("은은한 술맛", "찐한 술맛"),
        Pair("부드러운 목넘김", "화끈거리는 목넘김")
    )

    var selectedList by remember {
        mutableStateOf(listOf(0, 0, 0, 0))
    }

    if (!selectedList.contains(0)) {
        navigateSummaryPage(selectedList)
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        ProofTheme.color.black,
                        ProofTheme.color.black
                    )
                ),
                alpha = 0.7f
            )
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            ProofTheme.color.primary300,
                            ProofTheme.color.black
                        )
                    ),
                    alpha = 0.2f
                )
                .align(Alignment.Center)
        )

        LazyColumn(
            modifier = modifier.height(200.dp),
            state = lazyListState,
            flingBehavior = layoutInfo
        ) {
            itemsIndexed(radioTitles) { index, it ->
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp, top = 28.dp)
                ) {
                    Text(
                        text = it.first,
                        style = ProofTheme.typography.headingXS,
                        color = ProofTheme.color.white
                    )

                    Text(
                        text = it.second,
                        style = ProofTheme.typography.headingXS,
                        color = ProofTheme.color.white
                    )
                }

                var state by remember {
                    mutableStateOf(0)
                }

                val radioButtons = listOf(
                    Pair(1, 34),
                    Pair(2, 28),
                    Pair(3, 24),
                    Pair(4, 24),
                    Pair(5, 28),
                    Pair(6, 34)
                )

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 28.dp)
                ) {
                    radioButtons.forEach { radioButton ->
                        val item = radioButton.first
                        val radius = radioButton.second

                        IconToggleButton(checked = state == item, onCheckedChange = {
                            state = item
                            val temp = selectedList.toMutableList()
                            temp[index] = item
                            selectedList = temp
                        }) {
                            Icon(
                                painter = painterResource(
                                    if (state == item) {
                                        R.drawable.ic_taste_toggle_on
                                    } else {
                                        R.drawable.ic_taste_toggle_off
                                    }
                                ), contentDescription = "", modifier = Modifier
                                    .width(radius.dp)
                                    .height(radius.dp),
                                tint = Color.Unspecified
                            )
                        }
                    }
                }

            }
        }
    }

}

@Composable
fun SummarySelectOption(
    navigateSecondarySummaryPage: (String) -> Unit,
    modifier: Modifier
) {
    val optionContents = listOf(
        Pair(
            painterResource(id = R.drawable.ic_feeling_fruits),
            stringResource(R.string.feeling_fruits)
        ),
        Pair(
            painterResource(id = R.drawable.ic_feeling_fruits),
            stringResource(R.string.feeling_wood)
        ),
        Pair(
            painterResource(id = R.drawable.ic_feeling_fruits),
            stringResource(R.string.feeling_nooroongji)
        ),
        Pair(
            painterResource(id = R.drawable.ic_feeling_fruits),
            stringResource(R.string.feeling_milk)
        ),
        Pair(
            painterResource(id = R.drawable.ic_feeling_fruits),
            stringResource(R.string.feeling_earth)
        ),
        Pair(
            painterResource(id = R.drawable.ic_feeling_fruits),
            stringResource(R.string.feeling_flower)
        ),
        Pair(
            painterResource(id = R.drawable.ic_feeling_fruits),
            stringResource(R.string.feeling_groomy)
        ),
        Pair(
            painterResource(id = R.drawable.ic_feeling_fruits),
            stringResource(R.string.feeling_chili)
        ),
        Pair(
            painterResource(id = R.drawable.ic_feeling_fruits),
            stringResource(R.string.feeling_unknown)
        )
    )

    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        verticalArrangement = Arrangement.spacedBy(24.dp),
        horizontalArrangement = Arrangement.spacedBy(20.dp),
        modifier = Modifier.height(240.dp)
    ) {
        items(optionContents) { optionContent ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.clickable {
                    navigateSecondarySummaryPage("123")
                }
            ) {
                Image(
                    painter = optionContent.first,
                    contentDescription = "",
                    modifier = Modifier
                        .width(84.dp)
                        .height(84.dp)
                        .padding(bottom = 12.dp)
                )

                Text(
                    text = optionContent.second,
                    style = ProofTheme.typography.buttonS,
                    color = ProofTheme.color.white
                )
            }
        }
    }

}

@Composable
fun SecondarySummaryPage(
    modifier: Modifier
) {
    var content by remember {
        mutableStateOf("")
    }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "바쁘다면 패스해도 괜찮아요",
            style = ProofTheme.typography.bodyM,
            color = ProofTheme.color.gray200
        )
    }

    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            text = "이 술을 먹었던 장소는",
            style = ProofTheme.typography.headingS,
            color = ProofTheme.color.white
        )

        TextField(
            value = content,
            onValueChange = {
                content = it
            },
            placeholder = {
                Text(
                    text = "장소 입력하기",
                    color = ProofTheme.color.gray300
                )
            },
            modifier = Modifier
                .padding(top = 12.dp)
                .fillMaxWidth()
                .background(color = ProofTheme.color.gray600, shape = RoundedCornerShape(12.dp))
        )

        Text(
            text = "곁들인 안주의 종류는",
            style = ProofTheme.typography.headingS,
            color = ProofTheme.color.white,
            modifier = Modifier.padding(top = 44.dp)
        )

        Text(
            text = "여러 개 선택해도 돼요",
            style = ProofTheme.typography.bodyM,
            color = ProofTheme.color.gray200,
            modifier = Modifier.padding(top = 8.dp)
        )
    }

    val optionContents = listOf(
        Pair(
            painterResource(id = R.drawable.ic_menu_category),
            stringResource(R.string.summary_roast)
        ),
        Pair(
            painterResource(id = R.drawable.ic_menu_category),
            stringResource(R.string.summary_roast)
        ),
        Pair(
            painterResource(id = R.drawable.ic_menu_category),
            stringResource(R.string.summary_fry)
        ),
        Pair(
            painterResource(id = R.drawable.ic_menu_category),
            stringResource(R.string.summary_cheese)
        ),
        Pair(
            painterResource(id = R.drawable.ic_menu_category),
            stringResource(R.string.summary_salad)
        ),
        Pair(
            painterResource(id = R.drawable.ic_menu_category),
            stringResource(R.string.summary_fruits)
        ),
        Pair(
            painterResource(id = R.drawable.ic_menu_category),
            stringResource(R.string.summary_soup)
        ),
        Pair(
            painterResource(id = R.drawable.ic_menu_category),
            stringResource(R.string.summary_desert)
        ),
        Pair(
            painterResource(id = R.drawable.ic_menu_category),
            stringResource(R.string.summary_noodle)
        )
    )

    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        verticalArrangement = Arrangement.spacedBy(18.dp),
        horizontalArrangement = Arrangement.spacedBy(20.dp),
        modifier = Modifier
            .padding(top = 24.dp)
            .height(240.dp)
    ) {
        items(optionContents) { optionContent ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = optionContent.first,
                    contentDescription = "",
                    modifier = Modifier
                        .width(84.dp)
                        .height(84.dp)
                        .padding(bottom = 12.dp)
                )

                Text(
                    text = optionContent.second,
                    style = ProofTheme.typography.buttonS,
                    color = ProofTheme.color.white
                )
            }
        }
    }

    Button(
        onClick = { },
        modifier = Modifier
            .fillMaxWidth()
            .height(52.dp)
            .background(color = ProofTheme.color.primary300)
    ) {
        Text(
            text = "작성 완료",
            style = ProofTheme.typography.buttonL
        )
    }
}