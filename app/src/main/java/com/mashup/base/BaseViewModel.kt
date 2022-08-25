package com.mashup.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mashup.zuzu.data.network.NetworkMonitor
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * @Created by 김현국 2022/08/25
 */

open class BaseViewModel constructor(
    private val networkMonitor: NetworkMonitor
) : ViewModel() {

    private val _connection: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val connection = _connection.asStateFlow()

    init {
        viewModelScope.launch {
            networkMonitor.isConnected.collect {
                _connection.value = it
            }
        }
    }
}
