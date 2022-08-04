package com.mashup.zuzu.ui.category

import androidx.paging.PagingData
import com.mashup.zuzu.data.model.Wine
import kotlinx.coroutines.flow.Flow

/**
 * @Created by 김현국 2022/08/01
 */

sealed class WineListWithCategoryUiState {
    object Loading : WineListWithCategoryUiState()
    object Error : WineListWithCategoryUiState()
    data class Success(val wineList: List<Wine>) : WineListWithCategoryUiState()
}

sealed class WineListWithPageAndCategoryUiState {
    object Loading : WineListWithPageAndCategoryUiState()
    data class Success(val wineList: PagingData<Wine>) : WineListWithPageAndCategoryUiState()
}
