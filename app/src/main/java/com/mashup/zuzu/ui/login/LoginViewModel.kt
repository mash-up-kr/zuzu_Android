package com.mashup.zuzu.ui.login

import androidx.lifecycle.ViewModel
import com.mashup.zuzu.bridge.ProofPreference
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val proofPreference: ProofPreference
) : ViewModel() {

    sealed class Action {
        object ClickKakaoLogin : Action()
        object ClickSkip : Action()
    }

    private val channel = Channel<Action>(Channel.BUFFERED)
    val actionFlow: Flow<Action>
        get() = channel.receiveAsFlow()

    fun onClickKakaoLogin() {
        channel.trySend(Action.ClickKakaoLogin)
    }

    fun onClickSkip() {
        channel.trySend(Action.ClickSkip)
    }
}