package com.mashup.zuzu.ui.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mashup.zuzu.data.model.Category
import com.mashup.zuzu.data.model.CategoryModel
import com.mashup.zuzu.ui.component.CategoryCard
import com.mashup.zuzu.ui.theme.ZuzuAndroidTheme

/**
 * @Created by 김현국 2022/06/22
 * @Time 6:00 오후
 */

@Composable
fun TopNavigationBar(
    categories: List<Category> = CategoryModel.getCategories(),
    categoryClick: (String) -> Unit
) {
    Box() {
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            items(categories) { category ->
                CategoryCard(
                    categoryImg = painterResource(id = category.categoryImg),
                    categoryName = category.categoryName,
                    categoryClick = categoryClick
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewCategory() {
    ZuzuAndroidTheme() {
        TopNavigationBar(CategoryModel.getCategories(), {})
//
    }
}
