package com.mashup.zuzu.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mashup.zuzu.ui.theme.Black
import com.mashup.zuzu.ui.theme.ProofTheme

/**
 * @Created by 김현국 2022/07/05
 * @Time 3:16 오후
 */

@Composable
fun Button(
    modifier: Modifier,
    text: String,
    onButtonClick: () -> Unit
) {
    Box(
        modifier = modifier.background(color = ProofTheme.color.primary300, shape = RoundedCornerShape(8.dp)).clickable { onButtonClick() }
    ) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = text,
            style = ProofTheme.typography.buttonM,
            color = ProofTheme.color.white
        )
    }
}
@Composable
fun RoundedButton(
    modifier: Modifier,
    fontSize: TextUnit,
    onButtonClick: () -> Unit,
    text: String
) {
    Box(
        modifier = modifier.background(
            color = Black,
            shape = RoundedCornerShape(8.dp)
        )
            .border(
                width = 1.dp,
                color = ProofTheme.color.primary100, // 테두리 색깔
                shape = RoundedCornerShape(8.dp)
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier.padding(start = 6.dp, end = 6.dp, top = 4.dp, bottom = 4.dp),
            text = text,
            fontSize = fontSize,
            color = ProofTheme.color.primary100,
            textAlign = TextAlign.Center
        )
    }
}

@Preview
@Composable
fun PreviewRoundedButton() {
    ProofTheme {
        RoundedButton(modifier = Modifier.padding(top = 18.dp).width(140.dp).height(32.dp), fontSize = 16.sp, {}, "+ 내 술 저장고 추가")
    }
}

@Preview
@Composable
fun PreviewButton() {
    ProofTheme {
        Button(modifier = Modifier.width(115.dp).height(44.dp), text = "술추천 보기") {
//
        }
    }
}
