package com.mashup.zuzu.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mashup.zuzu.data.model.BestWorldCup
import com.mashup.zuzu.data.model.BestWorldCupRepo
import com.mashup.zuzu.data.model.Wine
import com.mashup.zuzu.data.model.WineRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * @Created by 김현국 2022/07/11
 * @Time 3:22 오후
 */

sealed class BestWorldCupUiState {
    object Loading : BestWorldCupUiState()
    object Error : BestWorldCupUiState()
    data class Success(val bestWorldCupList: List<BestWorldCup>) : BestWorldCupUiState()
}

sealed class RecommendWineUiState {
    object Loading : RecommendWineUiState()
    object Error : RecommendWineUiState()
    data class Success(val recommendWine: Wine) : RecommendWineUiState()
}

sealed class MainWineUiState {
    object Loading : MainWineUiState()
    object Error : MainWineUiState()
    data class Success(val mainWines: List<Wine>) : MainWineUiState()
}

class HomeViewModel : ViewModel() {

    private val _mainWineList: MutableStateFlow<MainWineUiState> = MutableStateFlow(MainWineUiState.Loading)
    val mainWineList = _mainWineList.asStateFlow()

    private val _bestWorldCupList: MutableStateFlow<BestWorldCupUiState> = MutableStateFlow(BestWorldCupUiState.Loading)
    val bestWorldCupList = _bestWorldCupList.asStateFlow()

    private val _recommendWine: MutableStateFlow<RecommendWineUiState> = MutableStateFlow(RecommendWineUiState.Loading)
    val recommendWine = _recommendWine.asStateFlow()

    init {
        getMainWineList()
        getBestWorldCupList()
    }

    private fun getMainWineList() {
        viewModelScope.launch {
            _mainWineList.value = MainWineUiState.Success(WineRepo.getWineData())
        }
    }

    private fun getBestWorldCupList() {
        viewModelScope.launch {
            _bestWorldCupList.value = BestWorldCupUiState.Success(BestWorldCupRepo.getBestWorldCupData())
        }
    }

    fun getRecommendWine() {
        viewModelScope.launch {
            _recommendWine.value = RecommendWineUiState.Success(WineRepo.getRecommendWine())
        }
    }
}
