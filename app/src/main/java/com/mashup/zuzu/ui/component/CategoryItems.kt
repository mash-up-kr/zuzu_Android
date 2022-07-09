package com.mashup.zuzu.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mashup.zuzu.ui.theme.LightGray

/**
 * @Created by 김현국 2022/07/03
 * @Time 6:05 오후
 */

data class Category(
    val imageText: String,
    val title: String
)

val categoryList = listOf(
    Category(imageText = "\uD83C\uDF7C", title = "전체"),
    Category(imageText = "\uD83C\uDF7A", title = "맥주"),
    Category(imageText = "\uD83E\uDD43", title = "위스키"),
    Category(imageText = "\uD83E\uDD43", title = "와인"),
    Category(imageText = "\uD83C\uDF78", title = "칵테일"),
)


@Composable
fun CategoryItems(
    modifier: Modifier,
    onCategoryClick: (Category) -> Unit
) {
    LazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        items(categoryList) { category ->
            CategoryCard(
                modifier = Modifier.width(52.dp).height(52.dp)
                    .background(color = LightGray, shape = RoundedCornerShape(8.dp)),
                category = category, onCategoryClick = onCategoryClick
            )
        }
    }
}
