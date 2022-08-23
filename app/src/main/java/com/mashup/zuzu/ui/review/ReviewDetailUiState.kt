package com.mashup.zuzu.ui.review

import com.mashup.zuzu.data.model.Wine
import com.mashup.zuzu.data.response.model.Result

sealed class WineDataUiState {
    object Loading : WineDataUiState()

    data class Success(val wineData: Wine) : WineDataUiState()
}

sealed class EvaluationUiState {
    object Loading : EvaluationUiState()

    object NoItem : EvaluationUiState()

    data class Success(val result: Result) : EvaluationUiState()
}
