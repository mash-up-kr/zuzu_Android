package com.mashup.zuzu.ui.home

import android.content.Context
import com.mashup.zuzu.ui.model.BestWorldCup
import com.mashup.zuzu.data.model.Category
import com.mashup.zuzu.data.model.Wine

/**
 * @Created by 김현국 2022/08/01
 */
sealed class HomeUiEvents {
    data class CategoryClick(val categoryList: List<Category>, val category: Category) : HomeUiEvents()
    data class WorldCupItemClick(val bestWorldCup: BestWorldCup) : HomeUiEvents()
    data class WineBoardClick(val wine: Wine) : HomeUiEvents()
    data class RefreshButtonClick(val context: Context) : HomeUiEvents()
}
