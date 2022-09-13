package com.mashup.zuzu.ui.review

import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.mashup.zuzu.R
import com.mashup.zuzu.compose.theme.ProofTheme

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
            painterResource(id = R.drawable.ic_review_write_weather_snow),
            stringResource(R.string.weather_snow)
        ),
        Pair(
            painterResource(id = R.drawable.ic_review_write_weather_sunny),
            stringResource(R.string.weather_sunny)
        ),
        Pair(
            painterResource(id = R.drawable.ic_review_write_weather_cloudy),
            stringResource(R.string.weather_cloudy)
        )
    )

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalArrangement = Arrangement.spacedBy(14.dp),
        modifier = modifier
    ) {
        items(optionContents) { optionContent ->
            val interactionSource = remember { MutableInteractionSource() }

            Button(
                onClick = {
                    val select = when (optionContent.second) {
                        "비 오는 날" -> "Rainy"
                        "눈 오는 날" -> "Snowy"
                        "맑은 날" -> "Sunny"
                        else -> "Cloudy"
                    }

                    navigateDateSelectPage(select)
                },
                colors = ButtonDefaults.buttonColors(backgroundColor = ProofTheme.color.gray600),
                modifier = Modifier
                    .height(52.dp)
                    .indication(
                        indication = rememberRipple(color = ProofTheme.color.primary300),
                        interactionSource = interactionSource
                    ),
                interactionSource = interactionSource

            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
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
}

@Composable
fun DateSelectOption(
    navigatePartnerPage: (String) -> Unit,
    modifier: Modifier
) {
    val optionContents = listOf(
        Pair(
            painterResource(id = R.drawable.ic_review_write_time_dinner),
            stringResource(R.string.date_dinner)
        ),
        Pair(
            painterResource(id = R.drawable.ic_review_write_time_lunch),
            stringResource(R.string.date_afternoon)
        ),
        Pair(
            painterResource(id = R.drawable.ic_review_write_time_night),
            stringResource(R.string.date_night)
        ),
        Pair(
            painterResource(id = R.drawable.ic_review_write_time_down),
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
            val interactionSource = remember { MutableInteractionSource() }

            Button(
                onClick = {
                    val select = when (optionContent.second) {
                        "저녁에" -> "Evening"
                        "대낮에" -> "Noon"
                        "새벽에" -> "Midnight"
                        else -> "Dawn"
                    }

                    navigatePartnerPage(select)
                },
                colors = ButtonDefaults.buttonColors(backgroundColor = ProofTheme.color.gray600),
                modifier = Modifier
                    .height(52.dp)
                    .indication(
                        indication = rememberRipple(color = ProofTheme.color.primary300),
                        interactionSource = interactionSource
                    ),
                interactionSource = interactionSource
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
        stringResource(R.string.partner_solo),
        stringResource(R.string.partner_friends),
        stringResource(R.string.partner_couple),
        stringResource(R.string.partner_party)
    )

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalArrangement = Arrangement.spacedBy(14.dp),
        modifier = modifier.height(130.dp)
    ) {
        itemsIndexed(optionContents) { index, optionContent ->
            val interactionSource = remember { MutableInteractionSource() }

            Button(
                onClick = {
                    when (index) {
                        0 -> {
                            navigateSoloPage("Alone")
                        }

                        else -> {
                            val select = when (optionContent) {
                                "친구/지인과" -> "Friends"
                                "연인과" -> "Lover"
                                else -> "Gather"
                            }

                            navigateGroupPage(select)
                        }
                    }
                },
                colors = ButtonDefaults.buttonColors(backgroundColor = ProofTheme.color.gray600),
                modifier = Modifier
                    .height(52.dp)
                    .indication(
                        indication = rememberRipple(color = ProofTheme.color.primary300),
                        interactionSource = interactionSource
                    ),
                interactionSource = interactionSource
            ) {
                Text(
                    text = optionContent,
                    style = ProofTheme.typography.headingXS,
                    color = ProofTheme.color.white
                )
            }
        }
    }
}

@Composable
fun GroupSelectOption(
    navigateTastePage: (Pair<String, Int>) -> Unit,
    modifier: Modifier
) {
    val optionContents = listOf(
        Pair(
            painterResource(id = R.drawable.ic_review_write_funny),
            stringResource(R.string.mood_friendly)
        ),
        Pair(
            painterResource(id = R.drawable.ic_review_write_serious),
            stringResource(R.string.mood_serious)
        ),
        Pair(
            painterResource(id = R.drawable.ic_review_write_romantic),
            stringResource(R.string.mood_romantic)
        ),
        Pair(
            painterResource(id = R.drawable.ic_review_write_crazy),
            stringResource(R.string.mood_crazy)
        ),
        Pair(
            painterResource(id = R.drawable.ic_review_write_groomy),
            stringResource(R.string.mood_groomy)
        ),
        Pair(
            painterResource(id = R.drawable.ic_review_write_congratulation),
            stringResource(R.string.mood_congratulations)
        )
    )

    var selectPair by remember {
        mutableStateOf(Pair("", 0))
    }

    var selectIndexPair by remember {
        mutableStateOf(Pair<Int?, Int?>(null, null))
    }

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalArrangement = Arrangement.spacedBy(14.dp),
        modifier = modifier.height(180.dp)
    ) {
        itemsIndexed(optionContents) { index, optionContent ->
            val interactionSource = remember { MutableInteractionSource() }

            Button(
                onClick = {
                    val result = when (optionContent.second) {
                        "친근한/웃기는" -> "Funny"
                        "엄격진지한" -> "Serious"
                        "로맨틱한" -> "Romantic"
                        "광란의" -> "Crazy"
                        "우울한" -> "Gloomy"
                        else -> "Celebratory"
                    }

                    selectPair = selectPair.copy(first = result)
                    selectIndexPair = selectIndexPair.copy(first = index)

                    if (selectPair.second != 0) {
                        navigateTastePage(selectPair)
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor =
                    if (selectIndexPair.first == index) ProofTheme.color.primary300
                    else ProofTheme.color.gray600
                ),
                modifier = Modifier
                    .height(52.dp)
                    .indication(
                        indication = rememberRipple(color = ProofTheme.color.primary300),
                        interactionSource = interactionSource
                    ),
                interactionSource = interactionSource
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
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.padding(top = 20.dp, start = 34.dp, end = 34.dp, bottom = 20.dp)
    ) {
        secondOptionContents.forEachIndexed { index, content ->
            val interactionSource = remember { MutableInteractionSource() }

            Button(
                onClick = {
                    val result = when (content) {
                        "1차" -> 1
                        "2차" -> 2
                        else -> 3
                    }

                    selectPair = selectPair.copy(second = result)
                    selectIndexPair = selectIndexPair.copy(second = index)

                    if (selectPair.first.isNotEmpty()) {
                        navigateTastePage(selectPair)
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor =
                    if (selectIndexPair.second == index) ProofTheme.color.primary300
                    else ProofTheme.color.gray600
                ),
                modifier = Modifier
                    .weight(1f)
                    .height(52.dp)
                    .indication(
                        indication = rememberRipple(color = ProofTheme.color.primary300),
                        interactionSource = interactionSource
                    ),
                interactionSource = interactionSource
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
    navigateTastePage: (Pair<String, Int?>) -> Unit,
    modifier: Modifier
) {
    val optionContents = listOf(
        Pair(
            painterResource(id = R.drawable.ic_review_write_funny),
            stringResource(R.string.mood_happy)
        ),
        Pair(
            painterResource(id = R.drawable.ic_review_write_serious),
            stringResource(R.string.mood_serious)
        ),
        Pair(
            painterResource(id = R.drawable.ic_review_write_groomy),
            stringResource(R.string.mood_groomy)
        ),
        Pair(
            painterResource(id = R.drawable.ic_review_write_crazy),
            stringResource(R.string.mood_crazy)
        ),
        Pair(
            painterResource(id = R.drawable.ic_review_write_taste),
            stringResource(R.string.mood_taste)
        ),
        Pair(
            painterResource(id = R.drawable.ic_review_write_congratulation),
            stringResource(R.string.mood_self_cong)
        )
    )

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalArrangement = Arrangement.spacedBy(14.dp),
        modifier = modifier.height(200.dp)
    ) {
        items(optionContents) { optionContent ->
            val interactionSource = remember { MutableInteractionSource() }

            Button(
                onClick = {
                    val result = when (optionContent.second) {
                        "친근한/웃기는" -> "Funny"
                        "엄격진지한" -> "Serious"
                        "로맨틱한" -> "Romantic"
                        "광란의" -> "Crazy"
                        "우울한" -> "Gloomy"
                        else -> "Celebratory"
                    }

                    navigateTastePage(Pair(result, null))
                },
                colors = ButtonDefaults.buttonColors(backgroundColor = ProofTheme.color.gray600),
                modifier = Modifier
                    .height(52.dp)
                    .indication(
                        indication = rememberRipple(color = ProofTheme.color.primary300),
                        interactionSource = interactionSource
                    ),
                interactionSource = interactionSource
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

// @OptIn(ExperimentalSnapperApi::class, ExperimentalPagerApi::class)
// @Composable
// fun TasteSelectOption(
//    navigateSummaryPage: (List<Int>) -> Unit,
//    modifier: Modifier
// ) {
// //    val layoutInfo = rememberSnapperFlingBehavior(lazyListState)
//    val coroutineScope = rememberCoroutineScope()
//
//    val radioTitles = listOf(
//        Pair("가벼워요", "무거워요"),
//        Pair("달아요", "써요"),
//        Pair("은은한 술맛", "찐한 술맛"),
//        Pair("부드러운 목넘김", "화끈거리는 목넘김")
//    )
//
//    val selectedStates = remember {
//        mutableStateMapOf<Int, Int>().apply {
//            radioTitles.mapIndexed { index, _ ->
//                index to 0
//            }.toMap().also {
//                putAll(it)
//            }
//        }
//    }
//
//    var selectedList = remember { mutableStateListOf(0, 0, 0, 0) }
//
//    val pagerState = rememberPagerState()
//
//    VerticalPager(
//        modifier = modifier,
//        state = pagerState,
//        contentPadding = PaddingValues(vertical = 100.dp),
//        count = radioTitles.size
//    ) { page ->
//
//        val backgroundWithPage = rememberColor(pagerState, page)
//        Column(
//            modifier = Modifier.height(123.dp).background(backgroundWithPage).graphicsLayer {
//                // Calculate the absolute offset for the current page from the
//                // scroll position. We use the absolute value which allows us to mirror
//                // any effects for both directions
//                val pageOffset = calculateCurrentOffsetForPage(page).absoluteValue
//
//                // We animate the scaleX + scaleY, between 85% and 100% // 가운데에 오면 아이템이 커짐
//                lerp(
//                    start = 1f,
//                    stop = 1f,
//                    fraction = 1f - pageOffset.coerceIn(0f, 1f)
//                ).also { scale ->
//                    scaleX = scale
//                    scaleY = scale
//                }
//
//                // We animate the alpha, between 50% and 100%
//                alpha = lerp(
//                    start = 0.5f,
//                    stop = 1f,
//                    fraction = 1f - pageOffset.coerceIn(0f, 1f)
//                )
//            }
//
//        ) {
//            Row(
//                horizontalArrangement = Arrangement.SpaceBetween,
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(bottom = 16.dp, top = 28.dp, start = 34.dp, end = 34.dp)
//            ) {
//                Text(
//                    text = radioTitles[page].first,
//                    style = ProofTheme.typography.headingXS,
//                    color = ProofTheme.color.white
//                )
//
//                Text(
//                    text = radioTitles[page].second,
//                    style = ProofTheme.typography.headingXS,
//                    color = ProofTheme.color.white
//                )
//            }
//
//            val radioButtons = remember {
//                listOf(
//                    Pair(1, 34),
//                    Pair(2, 28),
//                    Pair(3, 24),
//                    Pair(4, 24),
//                    Pair(5, 28),
//                    Pair(6, 34)
//                )
//            }
//
//            Row(
//                horizontalArrangement = Arrangement.SpaceBetween,
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(bottom = 28.dp, start = 34.dp, end = 34.dp)
//            ) {
//                radioButtons.forEach { radioButton ->
//                    val item = radioButton.first // index
//                    val radius = radioButton.second
//
//                    IconToggleButton(checked = selectedStates[page] == item, onCheckedChange = {
//                        selectedStates[page] = item
//                        coroutineScope.launch {
//                            if (page + 1 < radioTitles.size) {
//                                pagerState.animateScrollToPage(page + 1)
//                            }
//                        }
//                        if (page + 1 == radioTitles.size) {
//                            selectedStates.map {
//                                val index = it.key
//                                val value = it.value
//                                selectedList[index] = value
//                            }
//                            navigateSummaryPage(selectedList.toList())
//                        }
//                    }) {
//                        val painter = if (selectedStates[page] == item) {
//                            rememberAsyncImagePainter(
//                                ContextCompat.getDrawable(
//                                    LocalContext.current,
//                                    R.drawable.ic_radio_write_selected
//                                )
//                            )
//                        } else {
//                            rememberAsyncImagePainter(
//                                ContextCompat.getDrawable(
//                                    LocalContext.current,
//                                    R.drawable.ic_radio_write_unselected
//                                )
//                            )
//                        }
//
//                        Image(
//                            painter,
//                            contentDescription = "",
//                            modifier = Modifier
//                                .width(radius.dp)
//                                .height(radius.dp)
//                        )
//                    }
//                }
//            }
//        }
//    }
// //    }
// }

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
            painterResource(id = R.drawable.ic_feeling_tree),
            stringResource(R.string.feeling_wood)
        ),
        Pair(
            painterResource(id = R.drawable.ic_feeling_nooroongji),
            stringResource(R.string.feeling_nooroongji)
        ),
        Pair(
            painterResource(id = R.drawable.ic_feeling_milk),
            stringResource(R.string.feeling_milk)
        ),
        Pair(
            painterResource(id = R.drawable.ic_feeling_earth),
            stringResource(R.string.feeling_earth)
        ),
        Pair(
            painterResource(id = R.drawable.ic_feeling_flower),
            stringResource(R.string.feeling_flower)
        ),
        Pair(
            painterResource(id = R.drawable.ic_feeling_sad),
            stringResource(R.string.feeling_groomy)
        ),
        Pair(
            painterResource(id = R.drawable.ic_feeling_chili),
            stringResource(R.string.feeling_chili)
        ),
        Pair(
            painterResource(id = R.drawable.ic_feeling_unknown),
            stringResource(R.string.feeling_unknown)
        )
    )

    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        verticalArrangement = Arrangement.spacedBy(24.dp),
        horizontalArrangement = Arrangement.spacedBy(20.dp),
        modifier = modifier.height(360.dp)
    ) {
        items(optionContents) { optionContent ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.clickable {
                    val result = when (optionContent.second) {
                        "상큼달달한 과일" -> "Fruity"
                        "묵직한 나무" -> "Woody"
                        "구수한 누룽지" -> "Noroong"
                        "부드러운 우유" -> "Creamy"
                        "쿰쿰한 흙냄새" -> "Earthy"
                        "향긋한 꽃" -> "Flower"
                        "씁쓸한 인생" -> "Austere"
                        "매운 칠리" -> "Chilli"
                        else -> "Unknown"
                    }

                    navigateSecondarySummaryPage(result)
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
                    color = ProofTheme.color.white,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Composable
fun SecondarySummaryPage(
    modifier: Modifier,
    navigateReviewShareCard: (String, List<String>) -> Unit
) {
    var content by remember {
        mutableStateOf("")
    }

    var isSelectable by remember {
        mutableStateOf(mapOf<Int, Boolean>())
    }

    Column(
        modifier = Modifier.fillMaxWidth(),
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
                    style = ProofTheme.typography.bodyM,
                    color = ProofTheme.color.gray300
                )
            },
            textStyle = ProofTheme.typography.bodyM.copy(color = ProofTheme.color.gray300),
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
        Pair(stringResource(R.string.summary_roast), painterResource(id = R.drawable.ig_grilled)),
        Pair(stringResource(R.string.summary_sushi), painterResource(id = R.drawable.ig_sushi)),
        Pair(stringResource(R.string.summary_fry), painterResource(id = R.drawable.ig_fried)),
        Pair(stringResource(R.string.summary_cheese), painterResource(id = R.drawable.ig_cheese)),
        Pair(stringResource(R.string.summary_salad), painterResource(id = R.drawable.ig_salad)),
        Pair(stringResource(R.string.summary_fruits), painterResource(id = R.drawable.ig_fruits)),
        Pair(stringResource(R.string.summary_soup), painterResource(id = R.drawable.ig_soup)),
        Pair(stringResource(R.string.summary_desert), painterResource(id = R.drawable.ig_desert)),
        Pair(stringResource(R.string.summary_noodle), painterResource(id = R.drawable.ig_noodle))
    )

    val interactionSource = remember { MutableInteractionSource() }

    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        verticalArrangement = Arrangement.spacedBy(18.dp),
        horizontalArrangement = Arrangement.spacedBy(20.dp),
        modifier = Modifier
            .height(280.dp)
    ) {
        itemsIndexed(optionContents) { index, optionContent ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = if (isSelectable[index] == true) optionContent.second
                    else optionContent.second,
                    contentDescription = "",
                    modifier = Modifier
                        .width(52.dp)
                        .height(52.dp)
                        .padding(bottom = 12.dp)
                        .clickable {
                            isSelectable = if (isSelectable[index] == true) {
                                mapOf(Pair(index, false))
                            } else {
                                mapOf(Pair(index, true))
                            }
                        }
                )

                Text(
                    text = optionContent.first,
                    style = ProofTheme.typography.buttonS,
                    color = ProofTheme.color.white
                )
            }
        }
    }

    Button(
        onClick = {
            val result = mutableListOf<String>()

            isSelectable.forEach { index, isSelectable ->
                if (isSelectable) {
                    when (index) {
                        0 -> {
                            result.add("Grilled")
                        }
                        1 -> {
                            result.add("Fried")
                        }
                        2 -> {
                            result.add("Cheeze")
                        }
                        3 -> {
                            result.add("Salad")
                        }
                        4 -> {
                            result.add("Fruits")
                        }
                        5 -> {
                            result.add("Soup")
                        }
                        6 -> {
                            result.add("AppetizersSnacks")
                        }
                        7 -> {
                            result.add("Pasta")
                        }
                        else -> {
                            result.add("Pasta")
                        }
                    }
                }
            }

            navigateReviewShareCard(content, result)
        },
        colors = ButtonDefaults.buttonColors(backgroundColor = ProofTheme.color.primary300),
        modifier = Modifier
            .fillMaxWidth()
            .height(52.dp)
            .indication(
                indication = rememberRipple(color = ProofTheme.color.primary300),
                interactionSource = interactionSource
            ),
        interactionSource = interactionSource
    ) {
        Text(
            text = "작성 완료",
            style = ProofTheme.typography.buttonL,
            color = ProofTheme.color.white
        )
    }
}
