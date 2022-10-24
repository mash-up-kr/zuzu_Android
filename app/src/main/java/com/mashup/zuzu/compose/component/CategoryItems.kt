package com.mashup.zuzu.compose.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mashup.zuzu.data.model.Category
import com.mashup.zuzu.compose.theme.ProofTheme
import kotlinx.collections.immutable.ImmutableList

/**
 * @Created by 김현국 2022/07/03
 */

@Composable
fun CategoryItems(
    modifier: Modifier,
    categoryList: ImmutableList<Category>,
    onCategoryClick: (Category) -> Unit,
    childModifier: Modifier?
) {
    LazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(horizontal = 24.dp)
    ) {
        items(categoryList) { category ->
            CategoryCard(
                modifier = Modifier.width(52.dp).height(52.dp)
                    .background(color = ProofTheme.color.gray500, shape = CircleShape),
                category = category,
                onCategoryClick = onCategoryClick,
                childModifier = childModifier
            )
        }
    }
}
