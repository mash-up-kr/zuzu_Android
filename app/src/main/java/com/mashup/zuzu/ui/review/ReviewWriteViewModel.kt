package com.mashup.zuzu.ui.review

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mashup.zuzu.data.model.Results
import com.mashup.zuzu.data.model.wines
import com.mashup.zuzu.data.request.ReviewWriteRequest
import com.mashup.zuzu.domain.usecase.GetWineDataWithIdUseCase
import com.mashup.zuzu.domain.usecase.ReviewWriteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReviewWriteViewModel @Inject constructor(
    private val reviewWriteUseCase: ReviewWriteUseCase,
    private val getWineDataWithIdUseCase: GetWineDataWithIdUseCase,
    savedStateHandle: SavedStateHandle

) : ViewModel() {
    private var request = ReviewWriteRequest()
    private val page: MutableStateFlow<Int> = MutableStateFlow(0)

    val wineId: Long = savedStateHandle[WINE_ID] ?: 0L

    private val wineDataUiState: MutableStateFlow<WineDataUiState> =
        MutableStateFlow(WineDataUiState.Loading)

    init {
        getWineDataWithId(wineId)
    }

    val uiState: StateFlow<ReviewWriteUiState> =
        page.combine(wineDataUiState) { page, wineData ->
            if (wineData is WineDataUiState.Success) {
                ReviewWriteUiState(
                    page = page,
                    wineImageUrl = wineData.wineData.imageUrl,
                    wineName = wineData.wineData.name
                )
            } else {
                ReviewWriteUiState(
                    page = page,
                    wineImageUrl = "",
                    wineName = ""
                )
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = ReviewWriteUiState(
                page = 0,
                wineImageUrl = wines[0].imageUrl,
                wineName = wines[0].name
            )
        )

    private fun getWineDataWithId(wineId: Long) {
        viewModelScope.launch {
            getWineDataWithIdUseCase(id = wineId).collect { result ->
                when (result) {
                    is Results.Success -> {
                        wineDataUiState.value = WineDataUiState.Success(result.value)
                    }
                    is Results.Loading -> {
                    }
                    is Results.Failure -> {
                    }
                }
            }
        }
    }

    fun navigatePreviousWritePage() = viewModelScope.launch {
        val currentPage = page.value
        page.value =
            if (currentPage == 0) {
                0
            } else {
                if (currentPage in 3..4) {
                    2
                } else {
                    currentPage - 1
                }
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
            isHeavy = selectOptionList[0],
            isBitter = selectOptionList[1],
            isStrong = selectOptionList[2],
            isBurning = selectOptionList[3]
        )
        page.value = 6
    }

    fun navigateSecondarySummaryPage(selectOption: String) = viewModelScope.launch {
        request = request.copy(
            taste = selectOption
        )
        page.value = 7
    }

    fun navigateReviewShareCard(
        place: String,
        pairing: List<String>,
        navigateReviewShareCardScreen: (Long) -> Unit
    ) = viewModelScope.launch {
        request = request.copy(
            place = place,
            pairing = pairing
        )

        finishReviewWrite(navigateReviewShareCardScreen)
    }

    private fun finishReviewWrite(
        navigateReviewShareCardScreen: (Long) -> Unit
    ) = viewModelScope.launch {
        val reviewId = reviewWriteUseCase(
            wineId = wineId,
            reviewWriteRequest = request
        )

        navigateReviewShareCardScreen(reviewId)
    }

    companion object {
        const val WINE_ID = "wineId"
        const val WINE_IMAGE_URL = "wineImageUrl"
        const val WINE_NAME = "wineName"
    }
}
