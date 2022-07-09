package com.mashup.zuzu.ui.category

import android.util.Log
import androidx.compose.runtime.*

/**
 * @Created by 김현국 2022/07/05
 * @Time 4:20 오후
 */

val sortMenuItem = listOf("최신순", "오래된순", "인기순")

@Stable
class CategoryState(
    initSelectedTabIndex: Int,
    initExpanded: Boolean,
    initSortMenuItemText: String,
    initViewMode: Boolean
) {
    var selectedTabIndex by mutableStateOf(initSelectedTabIndex)
    var expanded by mutableStateOf(initExpanded)
    var sortMenuItemText by mutableStateOf(initSortMenuItemText)
    var viewMode by mutableStateOf(initViewMode)

    fun updateSelectedTabIndex(index: Int) {
        Log.d("", "CategoryState - updateSelectedTabIndex($index) called")
        selectedTabIndex = index
    }

    fun updateExpanded() {
        expanded = !expanded
    }
    fun updateSortMenuItemText(text: String) {
        sortMenuItemText = text
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
        initExpanded = false,
        initSortMenuItemText = sortMenuItem[0],
        initViewMode = true
    )
}
