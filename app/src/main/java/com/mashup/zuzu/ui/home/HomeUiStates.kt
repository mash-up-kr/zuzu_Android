package com.mashup.zuzu.ui.home

import com.mashup.zuzu.data.model.BestWorldCup
import com.mashup.zuzu.data.model.Category
import com.mashup.zuzu.data.model.Wine

/**
 * @Created by 김현국 2022/08/01
 */

sealed class BestWorldCupUiState {
    object Loading : BestWorldCupUiState()
    object Error : BestWorldCupUiState()
    data class Success(val bestWorldCupList: List<BestWorldCup>) : BestWorldCupUiState()
}

sealed class RecommendWineUiState {

    object Init : RecommendWineUiState()
    object Loading : RecommendWineUiState()
    object Error : RecommendWineUiState()
    data class Success(val recommendWine: Wine) : RecommendWineUiState()
}

sealed class MainWineUiState {
    object Loading : MainWineUiState()
    object Error : MainWineUiState()
    data class Success(val mainWines: List<Wine>) : MainWineUiState()
}

sealed class CategoryListUiState {
    object Loading : CategoryListUiState()
    object Error : CategoryListUiState()
    data class Success(val categoryList: List<Category>) : CategoryListUiState()
}
