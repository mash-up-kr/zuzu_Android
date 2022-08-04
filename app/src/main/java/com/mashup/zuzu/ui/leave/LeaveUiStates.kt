package com.mashup.zuzu.ui.leave

/**
 * @Created by 김현국 2022/08/01
 */
sealed class LeaveUiState {
    object Loading : LeaveUiState()
    object Error : LeaveUiState()
    object Success : LeaveUiState()
    object Init : LeaveUiState()
}