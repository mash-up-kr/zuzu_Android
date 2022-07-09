package com.mashup.zuzu.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mashup.zuzu.R
import com.mashup.zuzu.ui.theme.ProofTheme
import java.text.NumberFormat
import java.util.*

/**
 * @Created by 김현국 2022/07/01
 * @Time 7:13 오후
 */

data class BestWorldCup(
    val day: Int,
    val title: String,
    val participants: Int,
    val image: String
)

val bestWorldCupList = listOf(
    BestWorldCup(day = 5, title = "비오는 날 \n마시고 싶은 술은?", 14444, ""),
    BestWorldCup(day = 6, title = "더운 날 \n마시고 싶은 술은?", 14444, ""),
    BestWorldCup(day = 7, title = "밤에 \n마시고 싶은 술은?", 14444, ""),
    BestWorldCup(day = 8, title = "새벽에 \n마시고 싶은 술은?", 14444, ""),
    BestWorldCup(day = 9, title = "적적한 날 \n마시고 싶은 술은?", 14444, ""),
    BestWorldCup(day = 10, title = "기쁜 날 \n마시고 싶은 술은?", 14444, "")
)

@Composable
fun WorldCupCard(
    worldCupItem: BestWorldCup,
    onWorldCupItemClick: (BestWorldCup) -> Unit
) {
    Row(
        modifier = Modifier.height(127.dp).width(256.dp).clickable { onWorldCupItemClick(worldCupItem) }
            .background(color = ProofTheme.color.gray200, shape = RoundedCornerShape(9.dp))
            .padding(start = 16.dp, top = 16.dp, end = 16.dp, bottom = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column() {
            Box(
                modifier = Modifier.height(20.dp).width(29.dp).background(color = ProofTheme.color.primary300, shape = RoundedCornerShape(3.dp))
            ) {
                Text(
                    modifier = Modifier.align(Alignment.Center),
                    text = "D-${worldCupItem.day}",
                    style = ProofTheme.typography.bodyXS,
                    color = ProofTheme.color.gray300
                )
            }

            Text(
                modifier = Modifier.padding(top = 4.dp),
                text = worldCupItem.title,
                style = ProofTheme.typography.buttonL,
                color = ProofTheme.color.white
            )
            Text(
                modifier = Modifier.padding(top = 9.dp),
                text = "${NumberFormat.getNumberInstance(Locale.US).format(worldCupItem.participants)}명 참여중",
                style = ProofTheme.typography.bodyXS,
                color = ProofTheme.color.gray300
            )
        }
        Image(
            modifier = Modifier.height(95.dp).width(82.dp).clip(shape = RoundedCornerShape(7.31.dp)),
            painter = painterResource(id = R.drawable.img_wine_dummy),
            contentDescription = null,
            contentScale = ContentScale.FillBounds
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewWorldCupCard() {
    ProofTheme() {
        WorldCupCard(worldCupItem = bestWorldCupList[0], {})
    }
}
