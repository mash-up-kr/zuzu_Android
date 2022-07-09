package com.mashup.zuzu.ui.category

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * @Created by 김현국 2022/07/06
 * @Time 2:19 오후
 */
class CategoryViewModelFactory(private val category: String) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CategoryViewModel(category = category) as T
    }
}
