package com.mashup.zuzu.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.mashup.zuzu.R
import com.mashup.zuzu.data.model.Wine
import com.mashup.zuzu.data.model.wines
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
            Image(
                modifier = Modifier.fillMaxWidth().fillMaxHeight(),
                painter = painterResource(id = R.drawable.img_wine_dummy),
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
                        modifier = Modifier.fillMaxHeight().fillMaxWidth(),
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
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    painter = painterResource(id = R.drawable.img_wine_dummy),
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
                    modifier = Modifier.align(Alignment.BottomStart).padding(start = 12.dp, bottom = 12.dp),
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                ) {
                    Text(
                        modifier = Modifier.width(15.dp).height(12.dp),
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
                        modifier = Modifier.width(19.dp).height(12.dp),
                        text = "${wine.alc}%",
                        fontWeight = FontWeight.W400,
                        fontSize = 10.sp,
                        color = ProofTheme.color.white
                    )
                }
            }
        }
        Text(
            modifier = Modifier.height(44.dp).width(131.dp).padding(top = 8.dp),
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
                WineTagCard(tagDescription = tag)
            }
            if (wine.description.size >= 3) {
                OverflowText(count = wine.description.size - 2)
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun WineCardInHome(
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
            backgroundColor = Color.Cyan,
            elevation = 0.8.dp
        ) {
            Box() {
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    painter = painterResource(id = R.drawable.img_wine_dummy),
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
            }
        }
        Row(
            modifier = Modifier.padding(top = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            Text(
                modifier = Modifier.width(22.dp).height(14.dp),
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
                modifier = Modifier.width(15.dp).height(12.dp),
                text = "Alc",
                fontWeight = FontWeight.W700,
                fontSize = 10.sp,
                textAlign = TextAlign.Center,
                color = ProofTheme.color.white
            )
            Text(
                modifier = Modifier.width(19.dp).height(12.dp),
                text = "${wine.alc}%",
                fontWeight = FontWeight.W400,
                fontSize = 10.sp,
                color = ProofTheme.color.white
            )
        }
        Text(
            modifier = Modifier.height(49.dp).width(220.dp).padding(top = 8.dp),
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
                WineTagCard(tagDescription = tag)
            }
            if (wine.description.size >= 3) {
                OverflowText(count = wine.description.size - 2)
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
                modifier = Modifier.fillMaxHeight().fillMaxWidth()
            ) {
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp),
                    painter = painterResource(id = R.drawable.img_wine_dummy),
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds
                )

                Box(
                    modifier = Modifier.width(40.dp).height(40.dp)
                        .offset(x = 18.dp, y = 277.dp)
                        .background(color = Black, shape = RoundedCornerShape(6.04.dp)).zIndex(1.3f)
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
                        modifier = Modifier.fillMaxWidth().fillMaxHeight().padding(start = 18.dp, end = 22.dp, bottom = 19.dp, top = 29.dp)
                    ) {
                        Text(
                            modifier = Modifier.fillMaxWidth().height(44.dp),
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
                                modifier = Modifier.width(22.dp).height(20.dp),
                                text = "Alc",
                                fontWeight = FontWeight.W700,
                                fontSize = 14.sp,
                                textAlign = TextAlign.Center,
                                color = ProofTheme.color.gray500
                            )
                            Divider(
                                modifier = Modifier
                                    .background(ProofTheme.color.white)
                                    .height(10.dp)
                                    .width(1.dp)
                                    .align(Alignment.CenterVertically)
                            )
                            Text(
                                modifier = Modifier.width(29.dp).height(20.dp),
                                text = "${wine.alc}%",
                                fontWeight = FontWeight.W400,
                                fontSize = 14.sp,
                                color = ProofTheme.color.gray500
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
                                WineTagCard(tagDescription = tag)
                            }
                            if (wine.description.size >= 3) {
                                OverflowText(count = wine.description.size - 2)
                            }
                        }
                    }
                }
            }
        }
    }
}
@Composable
fun OverflowText(count: Int) {
    Text(
        text = "+$count",
        fontSize = 10.sp,
        color = ProofTheme.color.gray100
    )
}

@Composable
fun WineTagCard(tagDescription: String) {
    Box(
        modifier = Modifier.background(
            color = ProofTheme.color.gray500, // 태그 배경
            shape = RoundedCornerShape(8.dp)
        ).wrapContentWidth().height(22.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier.padding(start = 8.dp, end = 8.dp, top = 2.dp, bottom = 2.dp),
            text = tagDescription,
            fontSize = 11.sp,
            color = ProofTheme.color.gray100,
            maxLines = 1,
            textAlign = TextAlign.Center
        )
    }
}

@Preview
@Composable
fun PreviewWindImageCard() {
    ProofTheme() {
        WineImageCard(modifier = Modifier.height(412.dp).width(309.dp), wine = wines[0])
    }
}

@Preview
@Composable
fun PreviewWineTagCard() {
    ProofTheme() {
        WineTagCard(tagDescription = "비오는 날")
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewWindBoardCard() {
    ProofTheme() {
        WineBoardCard(modifier = Modifier.height(260.dp).width(220.dp), wine = wines[0], onWineBoardClick = {})
    }
}
@Preview(showBackground = true)
@Composable
fun PreviewPagerCard() {
    ProofTheme() {
        PagerWineCard(modifier = Modifier.width(282.dp).height(448.dp), wine = wines[0], onWineBoardClick = {})
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewWineCard2() {
    ProofTheme() {
        WineCardInHome(modifier = Modifier.width(220.dp).height(260.dp), wine = wines[0], onWineBoardClick = {})
    }
}
