package com.mashup.zuzu.ui.user.review

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Build
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import coil.ImageLoader
import coil.request.ImageRequest
import coil.request.SuccessResult
import com.google.android.renderscript.Toolkit
import com.mashup.base.BaseViewModel
import com.mashup.zuzu.data.model.Results
import com.mashup.zuzu.data.network.NetworkMonitor
import com.mashup.zuzu.domain.usecase.GetReviewsDrinksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus
import javax.inject.Inject

/**
 * @Created by 김현국 2022/08/11
 */
@HiltViewModel
class UserReviewDetailViewModel @Inject constructor(
    private val getReviewsDrinksUseCase: GetReviewsDrinksUseCase,
    networkMonitor: NetworkMonitor,
    savedStateHandle: SavedStateHandle
) : BaseViewModel(networkMonitor = networkMonitor) {

    private val _drinksId = mutableStateOf<Long>(0)

    private val _userReviewList: MutableStateFlow<UserReviewsDetailUiState> = MutableStateFlow(UserReviewsDetailUiState.Loading)
    val userReviewList = _userReviewList.asStateFlow()

    private val _bitmap: MutableStateFlow<Bitmap?> = MutableStateFlow(null)
    val bitmap = _bitmap.asStateFlow()

    fun updateDrinkId(wineId: Long) {
        _drinksId.value = wineId
    }

    fun getWineReviewListWithPage(
        context: Context
    ) {
        viewModelScope.launch {
            getReviewsDrinksUseCase(
                drinkId = _drinksId.value
            ).collect { result ->
                when (result) {
                    is Results.Success -> {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                            _userReviewList.value = UserReviewsDetailUiState.Success(
                                result.value
                            )
                        } else {
                            transBitmap(context, result.value.wine.imageUrl)
                            _bitmap.collect {
                                if (it != null) {
                                    val wine = result.value.wine.copy(
                                        bitmap = it
                                    )
                                    _userReviewList.value = UserReviewsDetailUiState.Success(
                                        result.value.copy(wine = wine)
                                    )
                                }
                            }
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
                    Toolkit.blur(it, 20)
                }
        }
    }
}
