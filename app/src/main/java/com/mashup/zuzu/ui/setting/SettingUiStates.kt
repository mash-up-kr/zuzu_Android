package com.mashup.zuzu.ui.setting

sealed class LogoutUiState {
    object Loading: LogoutUiState()
    object Success: LogoutUiState()
}