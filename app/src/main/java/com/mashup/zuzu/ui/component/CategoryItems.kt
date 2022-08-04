package com.mashup.zuzu.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mashup.zuzu.data.model.Category
import com.mashup.zuzu.data.model.categoryList
import com.mashup.zuzu.ui.theme.ProofTheme

/**
 * @Created by 김현국 2022/07/03
 */

@Composable
fun CategoryItems(
    modifier: Modifier,
    categoryList: List<Category>,
    onCategoryClick: (Category) -> Unit
) {
    LazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(categoryList) { category ->
            CategoryCard(
                modifier = Modifier.width(52.dp).height(52.dp)
                    .background(color = ProofTheme.color.gray500, shape = CircleShape),
                category = category,
                onCategoryClick = onCategoryClick
            )
        }
    }
}
