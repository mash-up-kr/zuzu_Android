package com.mashup.zuzu.ui.user

import com.mashup.zuzu.ui.model.BestWorldCup
import com.mashup.zuzu.data.model.Wine

/**
 * @Created by 김현국 2022/08/01
 */
sealed class UserUiEvents {
    data class SettingButtonClick(val userId: Long) : UserUiEvents()
    object EditButtonClick : UserUiEvents()
    data class WorldCupItemClick(val bestWorldCup: BestWorldCup) : UserUiEvents()
    data class WineItemClick(val wine: Wine) : UserUiEvents()
}
