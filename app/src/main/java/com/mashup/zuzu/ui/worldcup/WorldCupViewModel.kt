package com.mashup.zuzu.ui.worldcup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mashup.zuzu.data.model.Wine
import com.mashup.zuzu.data.model.WineRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

/**
 * @Created by 김현국 2022/06/21
 * @Time 4:23 오후
 */

class WorldCupViewModel(
    wineRepository: WineRepo
) : ViewModel() {

    private val _wineLists: MutableStateFlow<List<Wine>> = MutableStateFlow(wineRepository.getWineData())
    val wineLists: StateFlow<List<Wine>> get() = _wineLists

    val size by lazy { _wineLists.value.size }

    var count = 0
    var currentSelectedCount: MutableStateFlow<Int> = MutableStateFlow(size)
    private val _selectedWineLists: MutableStateFlow<MutableList<Wine>> = MutableStateFlow(mutableListOf())
    val selectedWineLists: StateFlow<List<Wine>> get() = _selectedWineLists

    private val _showWinePair: MutableStateFlow<List<Wine>> = MutableStateFlow(
        _wineLists.value.filterIndexed { index, wine ->
            index < 2
        }
    )
    val showWinePair: StateFlow<List<Wine>> get() = _showWinePair

    fun addWine(wine: Wine) {
        _selectedWineLists.value.add(wine)
        count++

        _showWinePair.value = _wineLists.value.filterIndexed { index, wine ->
            index in count * 2 until size
        }.take(2) // 두개씩 넣는데 만약에 2개가 안될 경우 우승

        if (_showWinePair.value.isEmpty()) {

            _wineLists.value = _selectedWineLists.value.map { it.copy() }
            _showWinePair.value = _wineLists.value.filterIndexed { index, wine ->
                index < 2
            }
            count = 0
            _selectedWineLists.value.clear()
            updateTopCount()
        }
    }

    fun updateTopCount() {
        currentSelectedCount.value /= 2
    }

    companion object {
        fun provideFactory(
            wineRepository: WineRepo = WineRepo
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return WorldCupViewModel(wineRepository = wineRepository) as T
            }
        }
    }
}
