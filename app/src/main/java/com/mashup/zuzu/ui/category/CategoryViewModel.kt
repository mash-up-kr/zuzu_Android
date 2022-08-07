package com.mashup.zuzu.ui.category

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mashup.zuzu.data.model.Results
import com.mashup.zuzu.data.model.Wine
import com.mashup.zuzu.domain.usecase.GetWineListWithPageAndCategoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @Created by 김현국 2022/07/03
 */

@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val getWineListWithPageAndCategoryUseCase: GetWineListWithPageAndCategoryUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _category = mutableStateOf("전체")
    val category = _category

    private val _wineListState: MutableStateFlow<WineListWithPageAndCategoryUiState> = MutableStateFlow(WineListWithPageAndCategoryUiState.Init)
    val wineListState = _wineListState.asStateFlow()

    private val _wineList: MutableStateFlow<List<Wine>> = MutableStateFlow(emptyList())

    var wineListScrollPosition: Int = 0

    val page = mutableStateOf(1)

    init {
        val argument = savedStateHandle.get<String>("category").orEmpty()
        updateCategory(category = argument)
    }

    fun onChangeWineListScrollPosition(position: Int) {
        wineListScrollPosition = position
    }

    fun getWineListWithPageAndCategory() {
        viewModelScope.launch {
            getWineListWithPageAndCategoryUseCase(
                category = _category.value,
                page = page.value
            ).collect { result ->
                when (result) {
                    is Results.Success -> {
                        _wineList.value = result.value
                        _wineListState.value = WineListWithPageAndCategoryUiState.Success(_wineList.value)
                    }
                    is Results.Loading -> {
                    }
                    is Results.Failure -> {
                    }
                }
            }
        }
    }

    fun getWineListWithPageAndCategoryNextPage() {
        viewModelScope.launch {
            if ((wineListScrollPosition + 1 >= page.value * PAGE_SIZE)) {
                incrementPage()
                if (page.value > 1) {
                    getWineListWithPageAndCategoryUseCase(
                        category = _category.value,
                        page = page.value
                    ).collect { result ->
                        when (result) {
                            is Results.Success -> {
                                _wineList.value = _wineList.value + result.value
                                _wineListState.value = WineListWithPageAndCategoryUiState.Success(_wineList.value)
                            }
                            is Results.Loading -> {
                            }
                            is Results.Failure -> {
                            }
                        }
                    }
                }
            }
        }
    }

    // 맨 처음 목록을 불러오는 기능과 , 이후 데이터를 가져오는 로직을 나눠야 한다.

    private fun incrementPage() {
        page.value = page.value + 1
    }

    fun updateCategory(category: String) {
        if (_category.value != category) {
            _wineList.value = emptyList()
            _wineListState.value = WineListWithPageAndCategoryUiState.Init
            _category.value = category
            page.value = 1
            onChangeWineListScrollPosition(0)
        }
    }

    val PAGE_SIZE = 8
}
