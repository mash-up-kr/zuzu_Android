package com.mashup.zuzu.ui.review

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mashup.zuzu.data.model.OptionWithEmoji
import com.mashup.zuzu.data.model.ReviewOption
import com.mashup.zuzu.data.repository.ReviewWriteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReviewWriteViewModel @Inject constructor(
    private val reviewWriteRepository: ReviewWriteRepository
) : ViewModel() {
    private val page: MutableStateFlow<Int> = MutableStateFlow(0)

    private val reviewOptionState: StateFlow<ReviewOption> =
        reviewWriteRepository.getSelectionWithTopic(0).stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = ReviewOption("", listOf())
        )

    val uiState: StateFlow<ReviewWriteType> =
        combine(
            reviewOptionState,
            page
        ) { reviewOptionState, page ->
            ReviewWriteType.ReviewWriteWithFourString(
                page = page,
                wineId = 0,
                topic = reviewOptionState.topic,
                options = reviewOptionState.options.map { it as OptionWithEmoji }
            )
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = ReviewWriteType.ReviewWriteWithFourString(0, 0, "", listOf())
        )

    fun selectPage(modifyPage: Int) = viewModelScope.launch {
        page.value = modifyPage
    }
}