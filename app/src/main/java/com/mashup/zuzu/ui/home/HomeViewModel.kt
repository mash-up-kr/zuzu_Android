package com.mashup.zuzu.ui.home

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Build
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.ImageLoader
import coil.request.ImageRequest
import coil.request.SuccessResult
import com.google.android.renderscript.Toolkit
import com.mashup.zuzu.data.mapper.worldCupResponseToUiModel
import com.mashup.zuzu.data.model.*
import com.mashup.zuzu.domain.usecase.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @Created by 김현국 2022/07/11
 */

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getRandomDrinksUseCase: GetRandomDrinksUseCase,
    private val getBestWorldCupListUseCase: GetBestWorldCupListUseCase,
    private val getRecommendDrinksUseCase: GetRecommendDrinksUseCase,
    private val getCategoryListUseCase: GetCategoryListUseCase
) : ViewModel() {

    private val _mainWineList: MutableStateFlow<MainWineUiState> =
        MutableStateFlow(MainWineUiState.Loading)
    val mainWineList = _mainWineList.asStateFlow()

    private val _bestWorldCupList: MutableStateFlow<BestWorldCupUiState> =
        MutableStateFlow(BestWorldCupUiState.Loading)
    val bestWorldCupList = _bestWorldCupList.asStateFlow()

    private val _recommendWine: MutableStateFlow<RecommendWineUiState> =
        MutableStateFlow(RecommendWineUiState.Init)
    val recommendWine = _recommendWine.asStateFlow()

    private val _categoryList: MutableStateFlow<CategoryListUiState> =
        MutableStateFlow(CategoryListUiState.Loading)
    val categoryList = _categoryList.asStateFlow()

    private val _bitmap: MutableStateFlow<Bitmap?> = MutableStateFlow(null)
    val bitmap = _bitmap.asStateFlow()

    init {
        getMainWineList()
        getBestWorldCupList()
        getCategoryList()
    }

    private fun getMainWineList() {
        viewModelScope.launch {
            getRecommendDrinksUseCase().collect { result ->
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
                        _bestWorldCupList.value = BestWorldCupUiState.Success(
                            worldCupResponseToUiModel(result.value)
                        )
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

    fun getRandomWine(
        context: Context
    ) {
        viewModelScope.launch {
            getRandomDrinksUseCase().collect { result ->
                when (result) {
                    is Results.Success -> {
                        _recommendWine.value = RecommendWineUiState.Success(result.value)
                        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.S) {
                            transBitmap(context, result.value.imageUrl)
                        }
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

    fun getCategoryList() {
        viewModelScope.launch {
            getCategoryListUseCase().collect { result ->
                when (result) {
                    is Results.Success -> {
                        _categoryList.value = CategoryListUiState.Success(result.value)
                    }
                    is Results.Loading -> {
                        _categoryList.value = CategoryListUiState.Loading
                    }
                    is Results.Failure -> {
                        _categoryList.value = CategoryListUiState.Error
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
