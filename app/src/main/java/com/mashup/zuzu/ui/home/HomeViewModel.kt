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
import com.mashup.zuzu.domain.usecase.GetBestWorldCupListUseCase
import com.mashup.zuzu.domain.usecase.GetMainWineListUseCase
import com.mashup.zuzu.domain.usecase.GetRecommendWineUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

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

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getMainWineListUseCase: GetMainWineListUseCase,
    private val getBestWorldCupListUseCase: GetBestWorldCupListUseCase,
    private val getRecommendWineUseCase: GetRecommendWineUseCase
) : ViewModel() {

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
            getMainWineListUseCase().collect { result ->
                when (result) {
                    is Results.Success -> {
                        _mainWineList.value = MainWineUiState.Success(result.value)
                    }
                    is Results.Loading -> {
                        _mainWineList.value = MainWineUiState.Loading
                    }
                    is Results.Failure -> {
                        _mainWineList.value = MainWineUiState.Error
                    }
                }
            }
        }
    }

    private fun getBestWorldCupList() {
        viewModelScope.launch {
            getBestWorldCupListUseCase().collect { result ->
                when (result) {
                    is Results.Success -> {
                        _bestWorldCupList.value = BestWorldCupUiState.Success(result.value)
                    }
                    is Results.Loading -> {
                        _bestWorldCupList.value = BestWorldCupUiState.Loading
                    }
                    is Results.Failure -> {
                        _bestWorldCupList.value = BestWorldCupUiState.Error
                    }
                }
            }
        }
    }

    fun getRecommendWine(
        context: Context
    ) {
        viewModelScope.launch {
            getRecommendWineUseCase().collect { result ->
                when (result) {
                    is Results.Success -> {
                        _recommendWine.value = RecommendWineUiState.Success(result.value)
                        // transBitmap(context, result.value.imageUrl)
                    }

                    is Results.Loading -> {
                        _recommendWine.value = RecommendWineUiState.Loading
                    }
                    is Results.Failure -> {
                        _recommendWine.value = RecommendWineUiState.Error
                    }
                }
            }
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
