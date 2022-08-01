package com.mashup.zuzu.ui.category

import androidx.compose.runtime.*

/**
 * @Created by 김현국 2022/07/05
 */

@Stable
class CategoryState(
    initSelectedTabIndex: Int,
    initViewMode: Boolean
) {
    var selectedTabIndex by mutableStateOf(initSelectedTabIndex)
    var viewMode by mutableStateOf(initViewMode)

    fun updateSelectedTabIndex(index: Int) {
        selectedTabIndex = index
    }

    fun changeViewMode() {
        viewMode = !viewMode
    }
}

@Composable
fun rememberCategoryState(
    index: Int // Home에서 선택하고 들어온 카테고리 인덱스
): CategoryState = remember() {
    CategoryState(
        initSelectedTabIndex = index,
        initViewMode = true
    )
}
