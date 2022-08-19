package com.mashup.zuzu.ui.launch

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mashup.zuzu.bridge.ProofPreference
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LaunchViewModel @Inject constructor(
    private val proofPreference: ProofPreference
) : ViewModel() {

    private val action = Channel<Action>(Channel.BUFFERED)
    val actionFlow: Flow<Action>
        get() = action.receiveAsFlow()

    sealed class Action {
        object StartLogin : Action()
        object StartMain : Action()
    }

    init {
        checkUserSigned()
    }

    private fun checkUserSigned() {
        viewModelScope.launch {
            delay(1000L)
            if (proofPreference.preference.getBoolean("login", false)) {
                action.trySend(Action.StartMain)
            } else {
                action.trySend(Action.StartLogin)
            }
        }
    }
}