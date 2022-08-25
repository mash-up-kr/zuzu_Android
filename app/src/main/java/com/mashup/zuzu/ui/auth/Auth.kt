package com.mashup.zuzu.ui.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mashup.zuzu.R
import com.mashup.zuzu.compose.component.Button
import com.mashup.zuzu.compose.theme.ProofTheme

/**
 * @Created by 김현국 2022/07/15
 */

@Composable
fun Auth(
    modifier: Modifier,
    onKakaoButtonClick: () -> Unit
) {
    Column(
        modifier = modifier.padding(top = 88.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier.width(90.dp).height(31.dp),
                painter = painterResource(id = R.drawable.img_logo),
                colorFilter = ColorFilter.tint(color = ProofTheme.color.white),
                contentDescription = null
            )
            Text(
                modifier = Modifier.padding(top = 20.dp).width(267.dp).height(68.dp),
                text = "지금 로그인하고\n내 술 저장고를 확인해보세요",
                style = ProofTheme.typography.headingXL,
                color = ProofTheme.color.white,
                textAlign = TextAlign.Center
            )
        }

        Row(
            modifier = Modifier.width(280.dp).height(280.dp).background(
                color = ProofTheme.color.primary200,
                shape = RoundedCornerShape(8.dp)
            )
        ) {}
        Button(
            modifier = Modifier.padding(bottom = 32.dp).width(312.dp).height(52.dp),
            backgroundColor = Color.Yellow,
            text = "카카오 로그인",
            textStyle = ProofTheme.typography.buttonM,
            onButtonClick = onKakaoButtonClick,
            textColor = ProofTheme.color.black
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAuth() {
    ProofTheme {
        Auth(modifier = Modifier.fillMaxWidth().fillMaxHeight().background(color = ProofTheme.color.black), {})
    }
}
