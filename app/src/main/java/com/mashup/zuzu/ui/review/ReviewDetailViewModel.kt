package com.mashup.zuzu.ui.review

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mashup.zuzu.data.model.ReviewDetailResponse
import com.mashup.zuzu.data.repository.ReviewDetailRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class ReviewDetailViewModel @Inject constructor(
    private val reviewDetailRepository: ReviewDetailRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val wineId = savedStateHandle.get<Long>("wineId") ?: 0L

    val uiState: StateFlow<ReviewDetailUiState> =
        reviewDetailRepository.getWineStream(wineId)
            .flatMapConcat {
                flow {
                    emit(
                        ReviewDetailUiState.Normal(
                            wine = it,
                            reviewDetailResponse = ReviewDetailResponse(),
                            dummyWorldCupData = DummyWorldCupData()
                        )
                    )
                }
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = ReviewDetailUiState.Loading
            )
}

data class DummyWorldCupData(
    val first: Int = 9999999,
    val fourth: Int = 3
)
