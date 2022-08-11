package com.mashup.zuzu.ui.review

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mashup.zuzu.data.model.Wine
import com.mashup.zuzu.data.model.wines
import com.mashup.zuzu.data.repository.ReviewWriteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReviewWriteViewModel @Inject constructor(
    private val reviewWriteRepository: ReviewWriteRepository
) : ViewModel() {
    private var request = ReviewWriteRequest()
    private val page: MutableStateFlow<Int> = MutableStateFlow(0)

    private val reviewWineState: StateFlow<Wine> =
        reviewWriteRepository.getReviewWineStream().stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = wines[0]
        )

    val uiState: StateFlow<ReviewWriteUiState> =
        combine(
            reviewWineState,
            page
        ) { reviewWineState, page ->
            ReviewWriteUiState(
                page = page,
                wine = reviewWineState
            )
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = ReviewWriteUiState(
                page = 0, wine = wines[0]
            )
        )

    fun navigatePreviousWritePage() = viewModelScope.launch {
        val currentPage = page.value
        page.value =
            if (currentPage == 0) {
                0
            } else {
                currentPage - 1
            }
    }

    fun navigateDateSelectPage(selectOption: String) = viewModelScope.launch {
        page.value = 1
    }

    fun navigatePartnerPage(selectOption: String) = viewModelScope.launch {
        page.value = 2
    }

    fun navigateGroupPage(selectOption: String) = viewModelScope.launch {
        page.value = 3
    }

    fun navigateSoloPage(selectOption: String) = viewModelScope.launch {
        page.value = 4
    }

    fun navigateTastePage(selectOption: String) = viewModelScope.launch {
        page.value = 5
    }

    fun navigateSummaryPage(selectOptionList: List<Int>) = viewModelScope.launch {
        page.value = 6
    }

    fun navigateSecondarySummaryPage(selectOption: String) = viewModelScope.launch {
        page.value = 7
    }

    fun completeReviewWrite() {
        //request 객체로 Api 요청
    }
}