package com.mashup.zuzu.ui.leave

/**
 * @Created by 김현국 2022/08/01
 */
sealed class LeaveUiEvents {
    object BackButtonClick : LeaveUiEvents()
    object LeaveButtonClick : LeaveUiEvents()
    object KeepUsingButtonClick : LeaveUiEvents()
}
