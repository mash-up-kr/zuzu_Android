package com.mashup.zuzu.compose.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.mashup.zuzu.R
import com.mashup.zuzu.ui.model.BestWorldCup
import com.mashup.zuzu.ui.model.bestWorldCupList
import com.mashup.zuzu.compose.theme.ProofTheme
import java.text.NumberFormat
import java.util.*

/**
 * @Created by 김현국 2022/07/01
 */

@Composable
fun WorldCupCard(
    modifier: Modifier,
    worldCupItem: BestWorldCup,
    onWorldCupItemClick: (BestWorldCup) -> Unit,
    childModifier: Modifier?
) {
    if (childModifier != null) {
        Row(
            modifier = modifier.clickable { onWorldCupItemClick(worldCupItem) }
                .background(color = ProofTheme.color.black)
        ) {
            Box {
                // Proof 태그 추가예정
                AsyncImage(
                    modifier = Modifier.height(88.dp).width(88.dp).clip(shape = CircleShape)
                        .then(childModifier),
                    model = worldCupItem.image,
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds
                )
            }

            Column(
                modifier = Modifier.fillMaxWidth().padding(start = 16.dp)
            ) {
                Box(
                    modifier = Modifier.width(78.dp).height(19.dp).then(childModifier)
                )
                Spacer(
                    modifier = Modifier.height(4.dp)
                )
                Box(
                    modifier = Modifier.width(208.dp).height(44.dp).then(childModifier)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Box(
                    modifier = Modifier.height(19.dp).width(87.dp).then(childModifier)
                )
            }
        }
    } else {
        Row(
            modifier = modifier.clickable { onWorldCupItemClick(worldCupItem) }
                .background(color = ProofTheme.color.black)
        ) {
            Box {
                // Proof 태그 추가예정
                AsyncImage(
                    modifier = Modifier.height(88.dp).width(88.dp).clip(shape = CircleShape),
                    model = worldCupItem.image,
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds
                )
            }

            Column(
                modifier = Modifier.fillMaxWidth().padding(start = 16.dp)
            ) {
                Text(
                    modifier = Modifier.padding(top = 4.dp),
                    text = worldCupItem.title,
                    maxLines = 2,
                    style = ProofTheme.typography.buttonL.copy(
                        fontWeight = FontWeight.Normal
                    ),
                    color = ProofTheme.color.white
                )
                Row(
                    modifier = Modifier.padding(top = 8.5.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_voted),
                        tint = ProofTheme.color.green200,
                        contentDescription = null
                    )
                    Text(
                        modifier = Modifier.padding(start = 5.dp),
                        text = "${
                        NumberFormat.getNumberInstance(Locale.US)
                            .format(worldCupItem.participants)
                        }명 참여",
                        style = ProofTheme.typography.bodyXS,
                        color = ProofTheme.color.gray300
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewWorldCupCard() {
    ProofTheme() {
        WorldCupCard(modifier = Modifier.fillMaxWidth(), worldCupItem = bestWorldCupList[0], {}, Modifier)
    }
}
