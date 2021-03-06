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
import com.mashup.zuzu.ui.category.Category
import com.mashup.zuzu.ui.category.categoryList
import com.mashup.zuzu.ui.theme.ProofTheme

/**
 * @Creatd by 김현국 2022/07/03
 * @Time 6:05 오후
 */



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
                    .background(color = ProofTheme.color.gray500, shape = RoundedCornerShape(16.dp)),
                category = category, onCategoryClick = onCategoryClick
            )
        }
    }
}
