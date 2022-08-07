package com.mashup.zuzu.ui.review

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

    //TODO: 이미지와 술이름만 있으면 되는데, Wine Model 사용이 필요할까?
    private val reviewWineState: StateFlow<Wine> =
        reviewWriteRepository.getReviewWineStream().stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = Wine(
                id = 1L,
                name = "GoldenBlueGoldenBlueGoldenBlueGoldenBlueGoldenBlueGoldenBlueGoldenBlueGoldenBlueGoldenBlueGoldenBlueGoldenBlueGoldenBlueGoldenBlueGoldenBlue",
                imageUrl = "https://images.absolutdrinks.com/ingredient-images/Raw/Absolut/65d43459-c926-4b12-a15b-afa7a71c2071.jpg?imwidth=500",
                price = 1000,
                alc = 17,
                description = "하이하이하이하이",
                favorite = true,
                category = "와인",
                tags = listOf("뜨는 술", "맛있는 술", "쓴 술", "단 술")
            )
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
                page = 0, wine = Wine(
                    id = 1L,
                    name = "GoldenBlueGoldenBlueGoldenBlueGoldenBlueGoldenBlueGoldenBlueGoldenBlueGoldenBlueGoldenBlueGoldenBlueGoldenBlueGoldenBlueGoldenBlueGoldenBlue",
                    imageUrl = "https://images.absolutdrinks.com/ingredient-images/Raw/Absolut/65d43459-c926-4b12-a15b-afa7a71c2071.jpg?imwidth=500",
                    price = 1000,
                    alc = 17,
                    description = "하이하이하이하이",
                    favorite = true,
                    category = "와인",
                    tags = listOf("뜨는 술", "맛있는 술", "쓴 술", "단 술")
                )
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

    //TODO: 페이지마다 선택해야하는 옵션의 수가 다르기 때문에, Navigate 역시 함수를 다 쪼갤 수 밖에 없다. 재사용이 가능한가?
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

    fun selectPage(modifyPage: Int) = viewModelScope.launch {
        page.value = modifyPage
    }
}