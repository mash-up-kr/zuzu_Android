package com.mashup.zuzu.ui.component

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mashup.zuzu.data.model.Wine
import com.mashup.zuzu.data.model.wines
import com.mashup.zuzu.R
import com.mashup.zuzu.ui.theme.ZuzuAndroidTheme
import com.mashup.zuzu.ui.theme.gradientBlack
import com.mashup.zuzu.ui.theme.gradientWhite
import com.mashup.zuzu.ui.theme.gray
import kotlinx.coroutines.launch

/**
 * @Created by 김현국 2022/06/25
 * @Time 5:39 오후
 */

@Composable
fun TagDescriptionCard(
    description: String
) {
    Card(
        shape = RoundedCornerShape(4.dp),
        backgroundColor = gray,
        modifier = Modifier.padding(5.dp)
    ) {
        Text(description)
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BoardItemCard(

    modifier: Modifier,
    wine: Wine,
    index: Int,
    onWineClick: (Wine, Int) -> Unit,
    onCardClick: () -> Unit
) {

    val backgroundGradient = listOf(gradientWhite, gradientBlack)

    Column(
        modifier = modifier.wrapContentSize()
    ) {
        Card(
            modifier = Modifier.width(150.dp).height(200.dp),
            shape = RoundedCornerShape(8.dp),
            onClick = {
                onWineClick(wine, index)
                onCardClick()
            }
        ) {
            Box() {
                Image(
                    modifier = Modifier.fillMaxWidth().fillMaxHeight(),
                    painter = painterResource(id = R.drawable.redwine),
                    contentDescription = null
                )
                Box(
                    modifier = Modifier.background(
                        brush = Brush.verticalGradient(backgroundGradient)
                    )
                        .fillMaxWidth()
                        .height(100.dp)
                        .align(Alignment.BottomCenter)
                )
                Text(
                    modifier = Modifier.align(Alignment.BottomStart)
                        .width(150.dp)
                        .height(70.dp).padding(10.dp),
                    text = "와인 텍스트", // $WineName
                    textAlign = TextAlign.Left,
                    color = Color.White,

                )
            }
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(5.dp),
            modifier = Modifier.padding(start = 5.dp)
        ) {
            Text(
                "ALC",
                fontWeight = FontWeight.Bold,
            )
            Text("${wine.alc}%")
        }
        LazyRow( // 추후 수정 LazyRow -> Row
            horizontalArrangement = Arrangement.spacedBy(5.dp),
            modifier = Modifier.width(150.dp)
        ) {
            items(wine.description) { description ->
                TagDescriptionCard(description = description)
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CategoryCard(
    categoryImg: Painter,
    categoryName: String,
    categoryClick: (String) -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier
                .width(52.dp)
                .height(52.dp),
            onClick = { categoryClick(categoryName) }, backgroundColor = gray,
            elevation = 0.dp
        ) {
            Image(
                painter = categoryImg, contentDescription = null,
                modifier = Modifier.padding(10.dp)
            )
        }
        Spacer(modifier = Modifier.fillMaxWidth().height(1.dp))
        Text(
            categoryName, textAlign = TextAlign.Center,
        )
    }
}

@Composable
fun WineCard(
    wine: Wine,
    index: Int,
    modifier: Modifier = Modifier,
    onWineClick: (Wine, Int) -> Unit,
    onCardClick: () -> Unit,
    clickState: Boolean,

) {

    val scaleA = remember { Animatable(initialValue = 1f) }

    LaunchedEffect(key1 = clickState) {
        if (clickState) {
            val job = launch {
                scaleA.animateTo(
                    targetValue = 1.3f,
                    animationSpec = tween(
                        durationMillis = 500
                    )
                )
                scaleA.animateTo(
                    targetValue = 1f,
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioLowBouncy,
                        stiffness = Spring.StiffnessLow
                    )
                )
            }
            job.join()
        }
    }
    Column(
        modifier = modifier
            .scale(scaleA.value)
            .wrapContentSize()
            .clickable(
                onClick = {
                    onWineClick(wine, index)
                    onCardClick()
                }
            )

    ) {
        Card(
            shape = RoundedCornerShape(15.dp),
            elevation = 5.dp
        ) {
            Image(
                modifier = Modifier
                    .height(400.dp)
                    .width(150.dp),
                painter = painterResource(id = wine.imageUrl),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
        }
        Text(
            text = "#${wine.name}"
        )
        Text(
            text = "#${wine.price}원"
        )
    }
}

@Composable
fun RoundCard(
    subDescription: String,
    mainRound: Int,
    modifier: Modifier,
    onRoundClick: (Int) -> Unit
) {
    Row(
        modifier = modifier.padding(start = 30.dp, end = 30.dp, top = 10.dp, bottom = 10.dp).background(
            color = gray,
            shape = RoundedCornerShape(8.dp)
        ).clickable {
            onRoundClick(mainRound)
        },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = modifier.padding(10.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = subDescription,
                style = TextStyle(
                    fontSize = 10.sp,
                    color = Color.LightGray
                )
            )
            Text(
                text = "${mainRound}강",
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            )
        }
    }
}

@Composable
fun WineCard2(
    wine: Wine,
    index: Int,
    modifier: Modifier = Modifier,
    onWineClick: (Wine, Int) -> Unit,
    onCardClick: () -> Unit,
    clickState: Boolean,
) {

    val scaleA = remember { Animatable(initialValue = 1f) }

    LaunchedEffect(key1 = clickState) {
        if (clickState) {
            val job = launch {
                scaleA.animateTo(
                    targetValue = 1.3f,
                    animationSpec = tween(
                        durationMillis = 500
                    )
                )
                scaleA.animateTo(
                    targetValue = 1f,
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioLowBouncy,
                        stiffness = Spring.StiffnessLow
                    )
                )
            }
            job.join()
        }
    }

    BoardItemCard(
        modifier = modifier,
        wine = wine,
        index = index,
        onWineClick = onWineClick,
        onCardClick = onCardClick
    )
}

@Preview
@Composable
fun PreviewWineCard() {
    ZuzuAndroidTheme() {
        WineCard(
            modifier = Modifier.width(150.dp).height(200.dp),
            wine = wines[0],
            index = 0,
            onWineClick = { _, _ -> },
            onCardClick = { /*TODO*/ },
            clickState = false
        )
//
    }
}

@Preview
@Composable
fun PreviewWineCard2() {
    ZuzuAndroidTheme() {
        WineCard2(wine = wines[0], index = 0, onWineClick = { _, _ -> }, onCardClick = { /*TODO*/ }, clickState = false)
//
    }
}

@Preview
@Composable
fun PreviewRoundCard() {
    ZuzuAndroidTheme() {
        RoundCard(
            subDescription = "심플하게", mainRound = 16, modifier = Modifier.width(200.dp).height(100.dp), {}
        )
//
    }
}

@Preview
@Composable
fun PreviewBoardItemCard() {
    ZuzuAndroidTheme() {
        // WineCard()
        // CategoryCard(painterResource(id = R.drawable.worldcup), "맥주", {})
        BoardItemCard(modifier = Modifier, wine = wines[0], 0, { _, _ -> }, {})
// modifier: Modifier,
//    wine: Wine,
//    index: Int,
//    onWineClick: (Wine, Int) -> Unit,
//    onCardClick: () -> Unit
    }
}

@Preview
@Composable
fun PreviewCategoryCard() {
    ZuzuAndroidTheme() {
        CategoryCard(categoryImg = painterResource(id = R.drawable.worldcup), categoryName = "맥주", categoryClick = {})
//
    }
}
