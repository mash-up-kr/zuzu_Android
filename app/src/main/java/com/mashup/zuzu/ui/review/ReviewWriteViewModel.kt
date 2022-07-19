package com.mashup.zuzu.ui.review

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mashup.zuzu.R
import com.mashup.zuzu.data.model.OptionWithEmoji
import com.mashup.zuzu.data.model.Wine
import com.mashup.zuzu.data.repository.ReviewWriteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReviewWriteViewModel @Inject constructor(
    private val reviewWriteRepository: ReviewWriteRepository
) : ViewModel() {
    private val selectDataList = listOf<Int>()

    private val page: MutableStateFlow<Int> = MutableStateFlow(0)

    private val reviewWineState: StateFlow<Wine> =
        reviewWriteRepository.getReviewWineStream().stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = Wine(
                id = 1L,
                name = "GoldenBlueGoldenBlueGoldenBlueGoldenBlueGoldenBlueGoldenBlueGoldenBlueGoldenBlueGoldenBlueGoldenBlueGoldenBlueGoldenBlueGoldenBlueGoldenBlue",
                imageUrl = R.drawable.img_wine_dummy,
                price = 1000,
                alc = 17,
                listOf("뜨는 술", "맛있는 술", "쓴 술", "단 술"),
                favorite = true,
                category = "와인"
            )
        )

    val uiState: StateFlow<ReviewWriteType> =
        combine(
            reviewWineState,
            page
        ) { reviewWineState, page ->
            reviewWriteRepository.getSelectionWithTopic(page).map { it ->
                ReviewWriteType.ReviewWriteWithFourString(
                    page = page,
                    wineId = reviewWineState.id,
                    topic = it.topic,
                    options = it.options.map { it as OptionWithEmoji }
                )
            }
        }.flatMapLatest { it }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = ReviewWriteType.ReviewWriteWithFourString(0, 0, "", listOf())
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

    /**
     * page를 2번째 페이지로 이동하고, 선택한 옵션을 뷰모델에서 로컬로 저장한다.
     */
    fun navigateTimeSelectPage(selectOption: String) = viewModelScope.launch {
        page.value = 1
    }

    fun selectPage(modifyPage: Int) = viewModelScope.launch {
        page.value = modifyPage
    }
}