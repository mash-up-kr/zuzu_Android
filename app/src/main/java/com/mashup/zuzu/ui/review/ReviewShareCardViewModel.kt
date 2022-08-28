package com.mashup.zuzu.ui.review

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mashup.zuzu.data.mapper.responseWineModelToDataModel
import com.mashup.zuzu.data.model.Results
import com.mashup.zuzu.data.model.ReviewShareCard
import com.mashup.zuzu.data.model.UserReview
import com.mashup.zuzu.domain.usecase.GetReviewShareWithIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ReviewShareCardViewModel @Inject constructor(
    private val getReviewShareWithIdUseCase: GetReviewShareWithIdUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val reviewId = savedStateHandle.get<Long>(REVIEW_ID) ?: 0

    private val _reviewShareCardState: MutableStateFlow<ReviewShareCard> = MutableStateFlow(
        ReviewShareCard()
    )
    val reviewShareCardState = _reviewShareCardState.asStateFlow()

    init {
        getReviewShareWithId()
    }

    private fun getReviewShareWithId() = viewModelScope.launch {
        getReviewShareWithIdUseCase(reviewId).collect {
            when (it) {
                is Results.Loading -> {
                    ReviewShareCard()
                }

                is Results.Success -> {
                    val review = it.value.userReview

                    _reviewShareCardState.value =
                        ReviewShareCard(
                            wine = responseWineModelToDataModel(it.value.drink),
                            userReview = UserReview(
                                id = review.id,
                                createdAt = review.createdAt,
                                mood = review.mood,
                                weather = review.weather,
                                time = review.time,
                                isHeavy = review.is_heavy,
                                isBitter = review.is_bitter,
                                isStrong = review.is_strong,
                                isBurning = review.is_burning,
                                taste = review.taste
                            )
                        )


                }

                is Results.Failure -> {
                    ReviewShareCard()
                }
            }

        }
    }

    companion object {
        const val REVIEW_ID = "reviewId"
    }
}