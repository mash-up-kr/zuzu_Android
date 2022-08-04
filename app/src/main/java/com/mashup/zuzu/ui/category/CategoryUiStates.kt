package com.mashup.zuzu.ui.category

import com.mashup.zuzu.data.model.Wine

/**
 * @Created by 김현국 2022/08/01
 */

sealed class WineListWithCategoryUiState {
    object Loading : WineListWithCategoryUiState()
    object Error : WineListWithCategoryUiState()
    data class Success(val wineList: List<Wine>) : WineListWithCategoryUiState()
}
