package com.mashup.zuzu.ui.review

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mashup.zuzu.data.repository.ReviewDetailRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class ReviewDetailViewModel @Inject constructor(
    private val reviewDetailRepository: ReviewDetailRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val wineId = savedStateHandle.get<Long>("wineId") ?: 0L

    val uiState: StateFlow<ReviewDetailUiState> =
        reviewDetailRepository.getReviewDetailInfoStream(wineId).stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = ReviewDetailUiState.Loading
        )
}