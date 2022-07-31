package com.mashup.zuzu.ui.component

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Refresh
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.drawscope.clipRect
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.mashup.zuzu.R
import com.mashup.zuzu.data.model.*
import com.mashup.zuzu.ui.theme.Black
import com.mashup.zuzu.ui.theme.ProofTheme

/**
 * @Created by 김현국 2022/07/01
 * @Time 3:23 오후
 */

val backgroundGradient = listOf(Color(0x00E4E8F5), Color(0xFF788098))

@Composable
fun WineImageCard(
    wine: Wine,
    modifier: Modifier
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(10.dp),
    ) {
        Box() {
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                model = ImageRequest.Builder(LocalContext.current).data(wine.imageUrl).build(),
                contentDescription = null,
                contentScale = ContentScale.FillBounds
            )
            Box(
                modifier = Modifier
                    .background(
                        brush = Brush.verticalGradient(colors = backgroundGradient),
                        alpha = 0.6f
                    )
                    .fillMaxWidth()
                    .fillMaxHeight(0.5f)
                    .align(Alignment.BottomCenter)
                    .padding(start = 24.dp, end = 24.dp, top = 39.dp, bottom = 20.dp)
            ) {
                CompositionLocalProvider(LocalContentColor provides ProofTheme.color.white) {
                    Column(
                        modifier = Modifier
                            .fillMaxHeight()
                            .fillMaxWidth(),
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {

                        Text(
                            text = wine.name,
                            maxLines = 4,
                            fontWeight = FontWeight.W400,
                            fontSize = 24.sp,
                            overflow = TextOverflow.Ellipsis
                        )
                        Row() {
                            Text(
                                text = "Alc",
                                fontWeight = FontWeight.W800,
                                fontStyle = FontStyle.Italic,
                                fontSize = 16.sp
                            )
                            Text(
                                modifier = Modifier.padding(start = 6.dp),
                                text = "${wine.alc}%"
                            )
                            Text(
                                modifier = Modifier.padding(start = 32.dp),
                                text = "산지 ",
                                fontWeight = FontWeight.W800,
                                fontSize = 16.sp
                            )
                            Text(
                                modifier = Modifier.padding(start = 6.dp),
                                text = "스코틀랜드", // 나중에 추가
                                fontWeight = FontWeight.W400,
                                fontSize = 16.sp
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun WineImageCardForReviewDetail(
    wine: Wine,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(10.dp),
    ) {
        Box() {
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                model = ImageRequest.Builder(LocalContext.current).data(wine.imageUrl).build(),
                contentDescription = null,
                contentScale = ContentScale.FillWidth
            )

            Box(
                modifier = Modifier
                    .background(
                        brush = Brush.verticalGradient(colors = backgroundGradient),
                        alpha = 0.6f
                    )
                    .fillMaxWidth()
                    .fillMaxHeight(0.5f)
                    .align(Alignment.BottomCenter)
                    .padding(start = 24.dp, end = 24.dp, top = 39.dp, bottom = 20.dp)
            ) {
                CompositionLocalProvider(LocalContentColor provides ProofTheme.color.white) {
                    Column(
                        modifier = Modifier
                            .fillMaxHeight()
                            .fillMaxWidth(),
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "${wine.category} Alc ${wine.alc}%",
                            style = ProofTheme.typography.bodyS600
                        )

                        Text(
                            text = wine.name,
                            maxLines = 4,
                            fontWeight = FontWeight.W400,
                            fontSize = 24.sp,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun WineBoardCard(
    modifier: Modifier,
    wine: Wine,
    onWineBoardClick: (Wine) -> Unit
) {

    Column() {
        Card(
            modifier = modifier,
            shape = RoundedCornerShape(4.dp),
            onClick = {
                onWineBoardClick(wine)
            },
            elevation = 0.8.dp
        ) {
            Box() {
                AsyncImage(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    model = ImageRequest.Builder(LocalContext.current).data(wine.imageUrl).build(),
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds
                )

                Box(
                    modifier = Modifier
                        .background(
                            brush = Brush.verticalGradient(colors = backgroundGradient),
                            alpha = 0.6f
                        )
                        .fillMaxWidth()
                        .height(100.dp)
                        .align(Alignment.BottomCenter)
                )
                Row(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(start = 12.dp, bottom = 12.dp),
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                ) {
                    Text(
                        modifier = Modifier
                            .width(17.dp)
                            .height(12.dp),
                        text = "Alc",
                        fontWeight = FontWeight.W700,
                        fontSize = 10.sp,
                        textAlign = TextAlign.Center,
                        color = ProofTheme.color.white
                    )
                    Divider(
                        modifier = Modifier
                            .background(ProofTheme.color.white)
                            .height(7.dp)
                            .width(1.dp)
                            .align(Alignment.CenterVertically)
                    )
                    Text(
                        modifier = Modifier
                            .width(22.dp)
                            .height(12.dp),
                        text = "${wine.alc}%",
                        fontWeight = FontWeight.W400,
                        fontSize = 10.sp,
                        color = ProofTheme.color.white
                    )
                }
            }
        }
        Text(
            modifier = Modifier
                .height(44.dp)
                .width(131.dp)
                .padding(top = 8.dp),
            text = wine.name,
            fontWeight = FontWeight.W700,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            fontSize = 14.sp,
            color = ProofTheme.color.white
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            wine.description.filterIndexed { index, tag ->
                index < 2
            }.map { tag ->
                WineTagCard(
                    tagDescription = tag,
                    backgroundColor = ProofTheme.color.gray500,
                    textColor = ProofTheme.color.gray50
                )
            }
            if (wine.description.size >= 3) {
                OverflowText(
                    count = wine.description.size - 2,
                    color = ProofTheme.color.gray300
                )
            }
        }
    }
}

@Composable
fun WineCategoryWithAlc(
    modifier: Modifier,
    wine: Wine
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier
                .width(23.dp)
                .height(14.dp),
            text = wine.category,
            fontWeight = FontWeight.W600,
            fontSize = 12.sp,
            color = ProofTheme.color.primary200
        )
        Divider(
            modifier = Modifier
                .background(ProofTheme.color.white)
                .height(7.dp)
                .width(1.dp)
                .align(Alignment.CenterVertically)
        )
        Text(
            modifier = Modifier
                .width(16.dp)
                .height(12.dp),
            text = "Alc",
            fontWeight = FontWeight.W700,
            fontSize = 10.sp,
            textAlign = TextAlign.Center,
            color = ProofTheme.color.white
        )
        Text(
            modifier = Modifier
                .width(20.dp)
                .height(12.dp),
            text = "${wine.alc}%",
            fontWeight = FontWeight.W400,
            fontSize = 10.sp,
            color = ProofTheme.color.white
        )
    }
}

@Composable
fun WineCardInHome(
    modifier: Modifier,
    height: Dp,
    wine: Wine,
    onWineBoardClick: (Wine) -> Unit
) {
    Column(
        modifier = modifier.clickable {
            onWineBoardClick(wine)
        }
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(4.dp),
            backgroundColor = Color.Cyan,
            elevation = 0.8.dp
        ) {
            Box() {
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(height),
                    painter = painterResource(id = R.drawable.exampleimg),
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )

                Box(
                    modifier = Modifier
                        .background(
                            brush = Brush.verticalGradient(colors = backgroundGradient),
                            alpha = 0.6f
                        )
                        .fillMaxWidth()
                        .height(100.dp)
                        .align(Alignment.BottomCenter)
                )
            }
        }
        WineCategoryWithAlc(modifier = Modifier.padding(top = 16.dp), wine = wine)
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            text = wine.name,
            fontWeight = FontWeight.W500,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            fontSize = 16.sp,
            color = ProofTheme.color.white
        )
        Row(
            modifier = Modifier.padding(top = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            wine.description.filterIndexed { index, tag ->
                index < 2
            }.map { tag ->
                WineTagCard(
                    tagDescription = tag,
                    backgroundColor = ProofTheme.color.gray500,
                    textColor = ProofTheme.color.gray50
                )
            }
            if (wine.description.size >= 3) {
                OverflowText(
                    count = wine.description.size - 2,
                    color = ProofTheme.color.gray100
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PagerWineCard(
    modifier: Modifier,
    wine: Wine,
    onWineBoardClick: (Wine) -> Unit
) {

    Column() {
        Card(
            modifier = modifier,
            shape = RoundedCornerShape(10.dp),
            onClick = {
                onWineBoardClick(wine)
            },
            elevation = 0.8.dp
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
            ) {
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp),
                    painter = painterResource(id = R.drawable.exampleimg),
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds
                )

                Box(
                    modifier = Modifier
                        .width(40.dp)
                        .height(40.dp)
                        .offset(x = 18.dp, y = 277.dp)
                        .background(color = Black, shape = RoundedCornerShape(6.04.dp))
                        .zIndex(1.3f)
                )

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(148.dp)
                        .align(Alignment.BottomCenter)
                        .background(
                            color = ProofTheme.color.white
                        )
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                            .padding(start = 18.dp, end = 22.dp, bottom = 19.dp, top = 29.dp)
                    ) {
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(44.dp),
                            text = wine.name,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis,
                            fontWeight = FontWeight.W700,
                            fontSize = 16.sp
                        )
                        Row(
                            modifier = Modifier.padding(top = 8.dp),
                            horizontalArrangement = Arrangement.spacedBy(4.dp),
                        ) {
                            Text(
                                modifier = Modifier
                                    .width(22.dp)
                                    .height(20.dp),
                                text = "Alc",
                                style = ProofTheme.typography.headingXS,
                                textAlign = TextAlign.Center,
                                color = ProofTheme.color.gray400
                            )
                            Text(
                                modifier = Modifier
                                    .width(29.dp)
                                    .height(20.dp),
                                text = "${wine.alc}%",
                                style = ProofTheme.typography.bodyM,
                                color = ProofTheme.color.gray400
                            )
                        }
                        Spacer(
                            modifier = Modifier.height(7.dp)
                        )
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp),
                        ) {
                            wine.description.filterIndexed { index, tag ->
                                index < 2
                            }.map { tag ->
                                WineTagCard(
                                    tagDescription = tag,
                                    backgroundColor = ProofTheme.color.gray50,
                                    textColor = ProofTheme.color.gray500
                                )
                            }
                            if (wine.description.size >= 3) {
                                OverflowText(
                                    count = wine.description.size - 2,
                                    color = ProofTheme.color.gray300
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun OverflowText(count: Int, color: Color) {
    Text(
        text = "+$count",
        style = ProofTheme.typography.body3XS,
        color = color
    )
}

@Composable
fun WineTagCard(tagDescription: String, backgroundColor: Color, textColor: Color) {
    Box(
        modifier = Modifier
            .background(
                color = backgroundColor, // 태그 배경
                shape = RoundedCornerShape(4.dp)
            )
            .wrapContentWidth()
            .height(22.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier.padding(start = 8.dp, end = 8.dp, top = 2.dp, bottom = 2.dp),
            text = tagDescription,
            style = ProofTheme.typography.body2XS600,
            color = textColor,
            maxLines = 1,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun RecommendWineCardWithRenderScript(
    modifier: Modifier,
    recommendWine: Wine,
    blurImage: Bitmap,
    onRefreshButtonClick: () -> Unit
) {
    var rowheight by remember {
        mutableStateOf<Dp?>(null)
    }
    Box(modifier = modifier) {
        RenderBlurImage(
            content = {
                AsyncImage(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(recommendWine.imageUrl)
                        .build(),
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )
            }, blurImage = {
                Image(
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth(),
                    bitmap = blurImage.asImageBitmap(),
                    contentScale = ContentScale.Crop,
                    contentDescription = null
                )
            }
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .drawWithContent {
                    clipRect(top = size.height / 1.4f) {
                        rowheight = (size.height - size.height / 1.4f).toDp()
                        this@drawWithContent.drawContent()
                    }
                }
        ) {
            rowheight?.let {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(it)
                        .align(Alignment.BottomCenter)
                        .padding(start = 20.dp, end = 20.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically

                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(0.75f)
                    ) {
                        Row {
                            WineCategoryWithAlc(wine = recommendWine, modifier = Modifier)
                        }
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(55.dp)
                                .padding(top = 8.dp),
                            text = recommendWine.name,
                            style = ProofTheme.typography.headingS.copy(
                                lineHeight = 22.sp
                            ),
                            color = ProofTheme.color.white,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(0.25f)
                            .padding(start = 20.dp),
                        horizontalAlignment = Alignment.End
                    ) {
                        Box(
                            modifier = Modifier
                                .width(46.dp)
                                .height(46.dp)
                                .background(
                                    color = ProofTheme.color.primary300,
                                    shape = RoundedCornerShape(8.dp)
                                )
                                .clickable { onRefreshButtonClick() }
                        ) {
                            Icon(
                                modifier = Modifier
                                    .width(16.dp)
                                    .height(16.dp)
                                    .align(Alignment.Center),
                                imageVector = Icons.Outlined.Refresh,
                                tint = ProofTheme.color.white,
                                contentDescription = null
                            )
                        }
                        Text(
                            modifier = Modifier.padding(top = 4.dp),
                            text = "다른술 보기",
                            style = ProofTheme.typography.body3XS,
                            color = ProofTheme.color.white
                        )
                    }
                }
            }
        }
    }
}

// 이미지를 두번 불러와서 블러처리할 부분만 자르고, 알파를 적용하는 방법
@Composable
fun RecommendWineCard(
    modifier: Modifier,
    recommendWine: Wine,
    onRefreshButtonClick: () -> Unit
) {
    var rowheight by remember {
        mutableStateOf<Dp?>(null)
    }
    Box(modifier = modifier) {
        BlurImage {
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                model = ImageRequest.Builder(LocalContext.current).data(recommendWine.imageUrl).build(),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .drawWithContent {
                    clipRect(top = size.height / 1.4f) {
                        rowheight = (size.height - size.height / 1.4f).toDp()
                        this@drawWithContent.drawContent()
                    }
                }
        ) {
            rowheight?.let {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(it)
                        .align(Alignment.BottomCenter)
                        .padding(start = 20.dp, end = 20.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically

                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(0.75f)
                    ) {
                        Row {
                            WineCategoryWithAlc(wine = recommendWine, modifier = Modifier)
                        }
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(55.dp)
                                .padding(top = 8.dp),
                            text = recommendWine.name,
                            style = ProofTheme.typography.headingS.copy(
                                lineHeight = 22.sp
                            ),
                            color = ProofTheme.color.white,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(0.25f)
                            .padding(start = 20.dp),
                        horizontalAlignment = Alignment.End
                    ) {
                        Box(
                            modifier = Modifier
                                .width(46.dp)
                                .height(46.dp)
                                .background(
                                    color = ProofTheme.color.primary300,
                                    shape = RoundedCornerShape(8.dp)
                                )
                                .clickable { onRefreshButtonClick() }
                        ) {
                            Icon(
                                modifier = Modifier
                                    .width(16.dp)
                                    .height(16.dp)
                                    .align(Alignment.Center),
                                imageVector = Icons.Outlined.Refresh,
                                tint = ProofTheme.color.white,
                                contentDescription = null
                            )
                        }
                        Text(
                            modifier = Modifier.padding(top = 4.dp),
                            text = "다른술 보기",
                            style = ProofTheme.typography.body3XS,
                            color = ProofTheme.color.white
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun RenderBlurImage(content: @Composable () -> Unit, blurImage: @Composable () -> Unit) {
    Box {
        content()
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .drawWithContent {
                    clipRect(top = size.height / 1.4f) {
                        val colors = listOf(Color.Transparent, Color.White)
                        this@drawWithContent.drawContent()
                        drawRect(
                            brush = Brush.verticalGradient(colors),
                            blendMode = BlendMode.DstIn
                        )
                    }
                }
        ) {
            blurImage()
        }
    }
}

@Composable
fun BlurImage(content: @Composable () -> Unit) {
    Box {
        content()
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .drawWithContent {
                    clipRect(top = size.height / 1.4f) {
                        val colors = listOf(Color.Transparent, Color.White)
                        this@drawWithContent.drawContent()
                        drawRect(
                            brush = Brush.verticalGradient(colors),
                            blendMode = BlendMode.DstIn
                        )
                    }
                }
                .blur(
                    radiusX = 10.dp, radiusY = 10.dp
                )
        ) {
            content()
        }
    }
}

@Composable
fun WineCellarCard(
    modifier: Modifier,
    wine: Wine,
    onWineClick: (Wine) -> Unit
) {
    Column(
        modifier = modifier.clickable {
            onWineClick(wine)
        }
    ) {
        Card() {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .aspectRatio(1f)
                    .background(color = ProofTheme.color.black)
            ) {
                AsyncImage(
                    modifier = Modifier.clip(CircleShape),
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(wine.imageUrl).build(),
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )
                Box(
                    modifier = Modifier
                        .width(22.dp)
                        .height(22.dp)
                        .background(color = ProofTheme.color.primary300)
                )
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = wine.name,
                style = ProofTheme.typography.bodyS600,
                color = ProofTheme.color.white,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            WineCategoryWithAlc(modifier = Modifier.padding(top = 4.dp), wine = wine)
        }
    }
}

@Preview
@Composable
fun PreviewWineCellarCard() {
    ProofTheme {
        WineCellarCard(
            modifier = Modifier
                .width(88.dp)
                .height(156.dp)
                .background(color = ProofTheme.color.black),
            wine = WineRepo.getWineData()[0],
            onWineClick = {}
        )
    }
}

@Preview
@Composable
fun PreviewWindImageCard() {
    ProofTheme() {
        WineImageCard(
            modifier = Modifier
                .height(412.dp)
                .width(309.dp),
            wine = wines[0]
        )
    }
}

@Preview
@Composable
fun PreviewWineTagCard() {
    ProofTheme() {
        WineTagCard(tagDescription = "비오는 날", backgroundColor = ProofTheme.color.black, textColor = ProofTheme.color.white)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewWindBoardCard() {
    ProofTheme() {
        WineBoardCard(
            modifier = Modifier
                .height(260.dp)
                .width(220.dp),
            wine = wines[0], onWineBoardClick = {}
        )
    }
}

@Preview(
    showBackground = true,
    name = "리뷰 디테일 용"
)
@Composable
fun PreviewWindBoardCardForReviewDetail() {
    ProofTheme() {
        WineImageCardForReviewDetail(
            modifier = Modifier
                .height(260.dp)
                .width(220.dp),
            wine = wines[0]
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewPagerCard() {
    ProofTheme() {
        PagerWineCard(
            modifier = Modifier
                .width(282.dp)
                .height(448.dp),
            wine = wines[0], onWineBoardClick = {}
        )
    }
}

@Preview
@Composable
fun PreviewRecommendWineCard() {
    ProofTheme() {
        RecommendWineCard(
            modifier = Modifier
                .fillMaxWidth()
                .height(367.dp)
                .padding(start = 24.dp, end = 24.dp, top = 19.dp)
                .clip(RoundedCornerShape(16.dp)),
            recommendWine = WineRepo.getRecommendWine(),
            onRefreshButtonClick = {
            }
        )
    }
}
