package com.mashup.zuzu.ui.user.review

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
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

    fun updateDrinkId(wineId: Long) {
        _drinksId.value = wineId
    }

    fun getWineReviewListWithPage() {
        viewModelScope.launch {
            getReviewsDrinksUseCase(
                drinkId = _drinksId.value
            ).collect { result ->
                when (result) {
                    is Results.Success -> {
                        _userReviewList.value = UserReviewsDetailUiState.Success(result.value)
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
