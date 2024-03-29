package com.mashup.zuzu.ui.setting

sealed class SettingUiEvents {
    object BackButtonClick : SettingUiEvents()
    object LogoutButtonClick : SettingUiEvents()
    object MoveToHome : SettingUiEvents()
    object LeaveButtonClick : SettingUiEvents()
    object EditButtonClick : SettingUiEvents()
}
