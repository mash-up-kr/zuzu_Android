package com.mashup.zuzu.compose.component

import androidx.compose.animation.Animatable
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.IconToggleButton
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.mashup.zuzu.R
import com.mashup.zuzu.compose.theme.HorizontalPurple
import com.mashup.zuzu.compose.theme.ProofTheme
import kotlinx.coroutines.launch

/**
 * @Created by 김현국 2022/09/13
 */

@Composable
fun TasteColumn(
    modifier: Modifier,
    navigateSummaryPage: (List<Int>) -> Unit,
    selectedList: List<Int>,
    currentIndex: Int,
    radioTitles: List<Pair<String, String>>,
    radioButtons: List<Pair<Int, Int>>,
    updateSelectedList: (Int, Int) -> Unit,
    updateCurrentIndex: (Int) -> Unit
) {
    val scrollState = rememberLazyListState()

    val coroutineScope = rememberCoroutineScope()

    val selectedStates = remember {
        mutableStateMapOf<Int, Int>().apply {
            radioTitles.mapIndexed { index, _ ->
                index to 0
            }.toMap().also {
                putAll(it)
            }
        }
    }

    LazyColumn(
        state = scrollState,
        modifier = modifier.fillMaxWidth().height(277.dp),
        contentPadding = PaddingValues(top = 82.dp)
    ) {
        itemsIndexed(radioTitles) { index, item ->

            // Start out gray and animate to green/red based on `ok`
            val color = remember { Animatable(Color.Transparent) }
            LaunchedEffect(currentIndex) {
                color.animateTo(if (currentIndex == index) HorizontalPurple else Color.Transparent)
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color.value)
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp, top = 28.dp, start = 34.dp, end = 34.dp)
                ) {
                    Text(
                        text = radioTitles[index].first,
                        style = ProofTheme.typography.headingXS,
                        color = ProofTheme.color.white
                    )
                    Text(
                        text = radioTitles[index].second,
                        style = ProofTheme.typography.headingXS,
                        color = ProofTheme.color.white
                    )
                }

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 28.dp, start = 34.dp, end = 34.dp)

                ) {
                    radioButtons.forEach { radioButton ->
                        val item = radioButton.first // index
                        val radius = radioButton.second

                        IconToggleButton(
                            checked = selectedStates[index] == item,
                            onCheckedChange = {
                                selectedStates[index] = item
                                coroutineScope.launch {
                                    if (index + 1 < radioTitles.size) {
                                        scrollState.animateScrollToItem(index + 1)
                                        updateCurrentIndex(index)
                                    }
                                }
                                if (index + 1 == radioTitles.size) {
                                    selectedStates.map {
                                        val index = it.key
                                        val value = it.value
                                        updateSelectedList(index, value)
                                    }
                                    navigateSummaryPage(selectedList.toList())
                                }
                            }
                        ) {
                            val painter = if (selectedStates[index] == item) {
                                painterResource(id = R.drawable.ic_radio_selected)
                            } else {
                                painterResource(id = R.drawable.ic_radio_unselected)
                            }
                            Image(
                                painter,
                                contentDescription = "",
                                modifier = Modifier
                                    .width(radius.dp)
                                    .height(radius.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}
