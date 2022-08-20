package com.mashup.zuzu.ui.review

import androidx.lifecycle.ViewModel
import com.mashup.zuzu.data.model.ReviewShareCard
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class ReviewShareCardViewModel @Inject constructor() : ViewModel() {
    private val _reviewShareCardState: MutableStateFlow<ReviewShareCard> = MutableStateFlow(ReviewShareCard())
    val reviewShareCardState: StateFlow<ReviewShareCard> = _reviewShareCardState.asStateFlow()
}