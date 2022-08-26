package com.mashup.zuzu.ui.leave

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mashup.zuzu.data.model.Results
import com.mashup.zuzu.domain.usecase.LeaveMembershipUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @Created by 김현국 2022/07/20
 */

@HiltViewModel
class LeaveViewModel @Inject constructor(
    private val leaveMembershipUseCase: LeaveMembershipUseCase
) : ViewModel() {

    private val _leave: MutableStateFlow<LeaveUiState> = MutableStateFlow(LeaveUiState.Init)
    val leave = _leave.asStateFlow()

    fun leaveMembership(userId: Long) {
        viewModelScope.launch {
            leaveMembershipUseCase(userId = userId).collect { result ->
                when (result) {
                    is Results.Success -> {
                        _leave.value = LeaveUiState.Success
                    }
                    is Results.Loading -> {
                        _leave.value = LeaveUiState.Loading
                    }
                    is Results.Failure -> {
                        _leave.value = LeaveUiState.Error
                    }
                }
            }
        }
    }
}
