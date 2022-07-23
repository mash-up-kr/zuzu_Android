package com.mashup.zuzu.ui.home

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.ImageLoader
import coil.request.ImageRequest
import coil.request.SuccessResult
import com.google.android.renderscript.Toolkit
import com.mashup.zuzu.data.model.*
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

    private val _bitmap: MutableStateFlow<Bitmap?> = MutableStateFlow(null)
    val bitmap = _bitmap.asStateFlow()

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

    fun getRecommendWine(
        context: Context
    ) {
        viewModelScope.launch {
            val s = WineRepo.getRecommendWine()
            _recommendWine.value = RecommendWineUiState.Success(s)
            transBitmap(context, s.imageUrl)
        }
    }

    // bit map 을 가져오는 것을 뷰모델에서
    fun transBitmap(context: Context, url: String) {
        val loading = ImageLoader(context)
        val request = ImageRequest.Builder(context)
            .data(url).build()

        viewModelScope.launch {
            _bitmap.value = (
                (
                    ((loading.execute(request) as SuccessResult).drawable)
                        as BitmapDrawable
                    ).bitmap
                ).copy(Bitmap.Config.ARGB_8888, true)
                .let {
                    Toolkit.blur(it, 10)
                }
        }
    }
}
