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

    var requestLoginFromOtherCases = false

    sealed class Action {
        object ClickKakaoLogin : Action()
        object StartMain : Action()
        object StartWorldcup : Action()
        object CloseLoginBySuccess : Action()
        object SkipLogin : Action()
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
                        if (!requestLoginFromOtherCases) {
                            startActivity()
                        } else {
                            channel.trySend(Action.CloseLoginBySuccess)
                        }
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
        if (!requestLoginFromOtherCases) {
            startActivity()
        } else {
            channel.trySend(Action.SkipLogin)
        }
    }

    private fun startActivity() {
        val isFirstRun = proofPreference.get(Key.Preference.IS_FIRST_RUN, true)
        if (!isFirstRun) {
            channel.trySend(Action.StartMain)
        } else {
            channel.trySend(Action.StartWorldcup)
            proofPreference.commit(Key.Preference.IS_FIRST_RUN, false)
        }
    }
}