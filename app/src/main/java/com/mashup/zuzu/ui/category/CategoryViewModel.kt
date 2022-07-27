package com.mashup.zuzu.ui.category

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mashup.zuzu.data.model.Results
import com.mashup.zuzu.data.model.Wine
import com.mashup.zuzu.domain.usecase.GetWineListUseCase
import com.mashup.zuzu.domain.usecase.GetWineListWithCategoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @Created by 김현국 2022/07/03
 * @Time 5:57 오후
 */

sealed class WineListWithCategoryUiState {
    object Loading : WineListWithCategoryUiState()
    object Error : WineListWithCategoryUiState()
    data class Success(val wineList: List<Wine>) : WineListWithCategoryUiState()
}
@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val getWineListUseCase: GetWineListUseCase,
    private val getWineListWithCategoryUseCase: GetWineListWithCategoryUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _wineList: MutableStateFlow<WineListWithCategoryUiState> =
        MutableStateFlow(WineListWithCategoryUiState.Loading)
    val wineList = _wineList.asStateFlow()

    init {
        val argument = savedStateHandle.get<String>("category").orEmpty()
        getWineList(category = argument)
    }

    fun getWineList(category: String) {
        viewModelScope.launch {
            if (category == "전체") {
                getWineListUseCase().collect { result ->
                    when (result) {
                        is Results.Success -> {
                            _wineList.value = WineListWithCategoryUiState.Success(result.value)
                        }
                        is Results.Loading -> {
                            _wineList.value = WineListWithCategoryUiState.Loading
                        }
                        is Results.Failure -> {
                            _wineList.value = WineListWithCategoryUiState.Error
                        }
                    }
                }
            } else {
                getWineListWithCategoryUseCase(category = category).collect { result ->
                    when (result) {
                        is Results.Success -> {
                            _wineList.value = WineListWithCategoryUiState.Success(result.value)
                        }
                        is Results.Loading -> {
                            _wineList.value = WineListWithCategoryUiState.Loading
                        }
                        is Results.Failure -> {
                            _wineList.value = WineListWithCategoryUiState.Error
                        }
                    }
                }
            }
        }
    }
}
