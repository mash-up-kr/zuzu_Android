package com.mashup.zuzu.ui.review

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mashup.zuzu.data.model.Wine
import com.mashup.zuzu.data.model.wines
import com.mashup.zuzu.data.repository.ReviewWriteRepository
import com.mashup.zuzu.data.request.ReviewWriteRequest
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
        request = request.copy(weather = selectOption)
        page.value = 1
    }

    fun navigatePartnerPage(selectOption: String) = viewModelScope.launch {
        request = request.copy(time = selectOption)
        page.value = 2
    }

    fun navigateGroupPage(selectOption: String) = viewModelScope.launch {
        request = request.copy(companion = selectOption)
        page.value = 3
    }

    fun navigateSoloPage(selectOption: String) = viewModelScope.launch {
        request = request.copy(companion = selectOption)
        page.value = 4
    }

    fun navigateTastePage(selectOption: Pair<String, Int?>) = viewModelScope.launch {
        request = request.copy(mood = selectOption.first, spot = selectOption.second)
        page.value = 5
    }

    fun navigateSummaryPage(selectOptionList: List<Int>) = viewModelScope.launch {
        request = request.copy(
            is_heavy = selectOptionList[0],
            is_bitter = selectOptionList[1],
            is_strong = selectOptionList[2],
            is_burning = selectOptionList[3]
        )
        page.value = 6
    }

    fun navigateSecondarySummaryPage(selectOption: String) = viewModelScope.launch {
        request = request.copy(
            taste = selectOption
        )
        page.value = 7
    }

    fun navigateReviewShareCard(place: String, pairing: List<String>) = viewModelScope.launch {
        request = request.copy(
            place = place,
            pairing = pairing
        )
    }

    fun finishReviewWrite() {
        //request 객체로 Api 요청
    }
}