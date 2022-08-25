package com.mashup.zuzu.compose.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mashup.zuzu.compose.theme.Black
import com.mashup.zuzu.compose.theme.ProofTheme

/**
 * @Created by 김현국 2022/07/05
 */

@Composable
fun Button(
    modifier: Modifier,
    text: String,
    textStyle: TextStyle,
    backgroundColor: Color,
    textColor: Color,
    onButtonClick: () -> Unit
) {
    Box(
        modifier = modifier
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(8.dp)
            )
            .clickable { onButtonClick() }
    ) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = text,
            style = textStyle,
            color = textColor
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
        modifier = modifier
            .background(
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

@Composable
fun CustomButtonGroup(
    modifier: Modifier,
    optionList: List<String>,
    selectionOption: String,
    onSelectionChange: (String) -> Unit
) {
    Row(
        modifier = modifier
    ) {
        optionList.forEach { text ->
            Column(
                modifier = Modifier
                    .weight(0.5f)
                    .fillMaxHeight()
                    .clickable {
                        onSelectionChange(text)
                    }, // 술 저장고 클릭
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = text,
                    style = ProofTheme.typography.headingXS,
                    color = if (text == selectionOption) {
                        ProofTheme.color.primary200
                    } else {
                        ProofTheme.color.white
                    },
                    textAlign = TextAlign.Center,
                )
            }
        }
    }
}
@Preview
@Composable
fun PreviewRoundedButton() {
    ProofTheme {
        RoundedButton(
            modifier = Modifier
                .padding(top = 18.dp)
                .width(140.dp)
                .height(32.dp),
            fontSize = 16.sp,
            {},
            "+ 내 술 저장고 추가"
        )
    }
}

@Preview
@Composable
fun PreviewButton() {
    ProofTheme {
        Button(
            modifier = Modifier
                .width(115.dp)
                .height(44.dp),
            text = "술추천 보기",
            textStyle = ProofTheme.typography.buttonM,
            backgroundColor = ProofTheme.color.primary300,
            textColor = ProofTheme.color.white
        ) {
//
        }
    }
}

@Preview
@Composable
fun PreviewButtonGroup() {
    ProofTheme {
        CustomButtonGroup(
            modifier = Modifier.padding(top = 24.dp)
                .height(54.dp)
                .width(170.dp),
            optionList = listOf("술 저장고", "참여한 술드컵"),
            selectionOption = "술 저장고",
            onSelectionChange = {}
        )
    }
}
