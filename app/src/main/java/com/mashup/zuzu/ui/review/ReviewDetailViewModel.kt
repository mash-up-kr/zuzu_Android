package com.mashup.zuzu.ui.review

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mashup.zuzu.data.mapper.mapperResultToKorean
import com.mashup.zuzu.data.mapper.mapperTasteListToKorean
import com.mashup.zuzu.data.model.Results
import com.mashup.zuzu.data.repository.ReviewDetailRepository
import com.mashup.zuzu.domain.usecase.GetDrinksEvaluationWithIdUseCase
import com.mashup.zuzu.domain.usecase.GetWineDataWithIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReviewDetailViewModel @Inject constructor(
    private val reviewDetailRepository: ReviewDetailRepository,
    private val getDrinksEvaluationWithIdUseCase: GetDrinksEvaluationWithIdUseCase,
    private val getWineDataWithIdUseCase: GetWineDataWithIdUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _wineDataUiState: MutableStateFlow<WineDataUiState> = MutableStateFlow(WineDataUiState.Loading)
    val wineDataUiState = _wineDataUiState.asStateFlow()

    private val _evalutaionUiState: MutableStateFlow<EvaluationUiState> = MutableStateFlow(EvaluationUiState.Loading)
    val evaluationUiState = _evalutaionUiState.asStateFlow()

    init {
        val argument = savedStateHandle.get<Long>(WINE_ID) ?: 0
        getDrinksEvaluationWithId(argument)
        getWineDataWithId(argument)
    }

    private fun getDrinksEvaluationWithId(wineId: Long) {
        viewModelScope.launch {
            getDrinksEvaluationWithIdUseCase(wineId = wineId).collect { result ->
                when (result) {
                    is Results.Success -> {
                        if (result.value.result == null) {
                            _evalutaionUiState.value = EvaluationUiState.NoItem
                        } else {
                            val transSituation = result.value.result.situation.map {
                                mapperResultToKorean(it)
                            }
                            val transTaste = mapperTasteListToKorean(result.value.result.taste)
                            val newResult = result.value.result.copy(
                                situation = transSituation,
                                taste = transTaste
                            )
                            _evalutaionUiState.value = EvaluationUiState.Success(newResult)
                        }
                    }
                    is Results.Loading -> {
                    }
                    is Results.Failure -> {
                    }
                }
            }
        }
    }

    private fun getWineDataWithId(wineId: Long) {
        viewModelScope.launch {
            getWineDataWithIdUseCase(id = wineId).collect { result ->
                when (result) {
                    is Results.Success -> {
                        _wineDataUiState.value = WineDataUiState.Success(result.value)
                    }
                    is Results.Loading -> {
                    }
                    is Results.Failure -> {
                    }
                }
            }
        }
    }

    companion object {
        const val WINE_ID = "wineId"
    }
}

data class DummyWorldCupData(
    val first: Int = 9999999,
    val fourth: Int = 3
)
