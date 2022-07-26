package com.mashup.zuzu.ui.category

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mashup.zuzu.data.model.Wine
import com.mashup.zuzu.data.model.WineRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * @Created by 김현국 2022/07/03
 * @Time 5:57 오후
 */

sealed class CategoryUiState {
    object Loading : CategoryUiState()
    object Error : CategoryUiState()
    data class Success(val wineList: List<Wine>) : CategoryUiState()
}

class CategoryViewModel(
    category: String
) : ViewModel() {

    private val _wineList: MutableStateFlow<CategoryUiState> =
        MutableStateFlow(CategoryUiState.Loading)
    val wineList = _wineList.asStateFlow()

    init {
        getWineList(category = category)
    }

    fun getWineList(category: String) {
        viewModelScope.launch {
            if (category == "전체") {
                _wineList.value = CategoryUiState.Success(WineRepo.getWineData())
            } else {
                _wineList.value = CategoryUiState.Success(WineRepo.getWineDataWithCategory(category = category))
            }
        }
    }
}
