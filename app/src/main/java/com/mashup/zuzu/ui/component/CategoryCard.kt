package com.mashup.zuzu.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mashup.zuzu.ui.theme.LightGray
import com.mashup.zuzu.ui.theme.White
import com.mashup.zuzu.ui.theme.ZuzuAndroidTheme

/**
 * @Created by 김현국 2022/07/01
 * @Time 6:49 오후
 */

@Composable
fun CategoryCard(
    modifier: Modifier,
    category: Category,
    onCategoryClick: (Category) -> Unit
) {
    Column(
        modifier = Modifier.clickable { onCategoryClick(category) }
    ) {
        Box(
            modifier = modifier
        ) {
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = category.imageText
            )
        }
        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally).padding(top = 5.dp),
            text = category.title,
            color = White
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCategoryCard() {
    ZuzuAndroidTheme() {
        CategoryCard(modifier = Modifier.width(52.dp).height(52.dp).background(color = LightGray, shape = RoundedCornerShape(8.dp)), category = categoryList[0], {})
    }
}
