package com.mashup.zuzu.ui.setting

sealed class SettingUiEvents {
    object BackButtonClick : SettingUiEvents()
    object LeaveButtonClick : SettingUiEvents()
    object EditButtonClick : SettingUiEvents()
}
