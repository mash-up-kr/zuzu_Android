package com.mashup.zuzu.ui.user.review

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus
import javax.inject.Inject

/**
 * @Created by 김현국 2022/08/11
 */
@HiltViewModel
class UserReviewDetailViewModel @Inject constructor() : ViewModel() {

    var reviewListScrollPosition: Int = 0
    val page = mutableStateOf(1)
    private val reviewMaxPage: MutableStateFlow<Int> = MutableStateFlow(0)

    fun onChangeReviewListScrollPosition(position: Int) {
        reviewListScrollPosition = position
    }

    fun getWineReviewListWithPage() {
        viewModelScope.launch { }
    }

    fun getWineReviewListWithPage() {
        viewModelScope.launch { }
    }

    private fun incrementPage() {
        page.value += 1
    }

    val PAGE_SIZE = 4
}
