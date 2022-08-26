package com.mashup.zuzu

import com.mashup.base.BaseViewModel
import com.mashup.zuzu.data.network.NetworkMonitor
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * @Created by 김현국 2022/08/25
 */

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    networkMonitor: NetworkMonitor
) : BaseViewModel(networkMonitor = networkMonitor)
