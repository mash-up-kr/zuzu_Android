package com.mashup.zuzu.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mashup.zuzu.bridge.ProofPreference
import com.mashup.zuzu.data.model.Results
import com.mashup.zuzu.domain.usecase.GetProofAuthDataUseCase
import com.mashup.zuzu.util.Key
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val proofPreference: ProofPreference,
    private val getProofAuthDataUseCase: GetProofAuthDataUseCase
) : ViewModel() {

    sealed class Action {
        object ClickKakaoLogin : Action()
        object ClickSkip : Action()
        object ProofAuthSuccess : Action()
        object ProofAuthFailed : Action()
    }

    private val channel = Channel<Action>(Channel.BUFFERED)
    val actionFlow: Flow<Action>
        get() = channel.receiveAsFlow()

    fun getProofAuthData(kakaoAccessToken: String) {
        viewModelScope.launch {
            getProofAuthDataUseCase(kakaoAccessToken = kakaoAccessToken).collect { result ->
                when (result) {
                    is Results.Success -> {
                        with(proofPreference) {
                            commit(Key.Preference.ACCESS_TOKEN, result.value.accessToken)
                            commit(Key.Preference.REFRESH_TOKEN, result.value.refreshToken)
                        }
                        channel.trySend(Action.ProofAuthSuccess)
                    }
                    is Results.Failure -> {
                        channel.trySend(Action.ProofAuthFailed)
                    }
                    is Results.Loading -> {}
                }
            }
        }
    }

    fun onClickKakaoLogin() {
        channel.trySend(Action.ClickKakaoLogin)
    }

    fun onClickSkip() {
        channel.trySend(Action.ClickSkip)
    }
}