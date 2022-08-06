package com.mashup.zuzu.ui.review

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
                            dummyWorldCupData = DummyWorldCupData(),
                            dummyWineReview = DummyWineReview()
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

//TODO: 서버 데이터 구조가 확정되기 전 임시로 지정한 데이터 클래스
data class DummyWorldCupData(
    val first: Int = 9999999,
    val fourth: Int = 3
)

data class DummyWineReview(
    val emoji: List<String> = listOf("즐거운", "친구와 함께", "눈 오는 날"),
    val sweetOrBitter: Pair<Int, Int> = Pair(60, 30),
    val lightOrHeavy: Pair<Int, Int> = Pair(100, 10),
    val softOrDeep: Pair<Int, Int> = Pair(20, 80),
    val mildOrHot: Pair<Int, Int> = Pair(30, 90),
    val foods: List<String> = listOf("치즈", "샐러드", "샐러드")
)