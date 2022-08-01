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

sealed class CategoryUiState {
    object Loading : CategoryUiState()
    object Error : CategoryUiState()
    data class Success(val wineList: List<Wine>) : CategoryUiState()
}
@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val getWineListUseCase: GetWineListUseCase,
    private val getWineListWithCategoryUseCase: GetWineListWithCategoryUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _wineList: MutableStateFlow<CategoryUiState> =
        MutableStateFlow(CategoryUiState.Loading)
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
                            _wineList.value = CategoryUiState.Success(result.value)
                        }
                        is Results.Loading -> {
                            _wineList.value = CategoryUiState.Loading
                        }
                        is Results.Failure -> {
                            _wineList.value = CategoryUiState.Error
                        }
                    }
                }
            } else {
                getWineListWithCategoryUseCase(category = category).collect { result ->
                    when (result) {
                        is Results.Success -> {
                            _wineList.value = CategoryUiState.Success(result.value)
                        }
                        is Results.Loading -> {
                            _wineList.value = CategoryUiState.Loading
                        }
                        is Results.Failure -> {
                            _wineList.value = CategoryUiState.Error
                        }
                    }
                }
            }
        }
    }
}
