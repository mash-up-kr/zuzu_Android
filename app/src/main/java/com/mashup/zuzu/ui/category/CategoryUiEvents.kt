package com.mashup.zuzu.ui.category

import com.mashup.zuzu.data.model.Wine

/**
 * @Created by 김현국 2022/08/01
 */

sealed class CategoryUiEvents {
    data class WineBoardClick(val wine: Wine) : CategoryUiEvents()
    object BackButtonClick : CategoryUiEvents()
    data class TabClick(val tag: String) : CategoryUiEvents()
}
