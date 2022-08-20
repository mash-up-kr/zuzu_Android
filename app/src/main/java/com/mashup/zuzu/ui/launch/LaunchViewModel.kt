package com.mashup.zuzu.ui.launch

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mashup.zuzu.bridge.ProofPreference
import com.mashup.zuzu.data.model.Results
import com.mashup.zuzu.data.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LaunchViewModel @Inject constructor(
    private val proofPreference: ProofPreference,
    private val repository: AuthRepository
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
            val refreshToken = proofPreference.get("refreshToken", "")
            if (refreshToken.isEmpty()) {
                action.trySend(Action.StartLogin)
            } else {
                getKakaoAccessToken(refreshToken)
            }
        }
    }

    private fun getKakaoAccessToken(refreshToken: String) {
        viewModelScope.launch {
            repository.getKakaoAccessToken(refreshToken = refreshToken).collect { result ->
                when (result) {
                    is Results.Success -> {
                        proofPreference.commit("accessToken", result.value)
                        action.trySend(Action.StartMain)
                    }
                    is Results.Failure -> {
                        with(proofPreference) {
                            commit("accessToken", null)
                            commit("refreshToken", null)
                        }
                        action.trySend(Action.StartLogin)
                    }
                    is Results.Loading -> {

                    }
                }
            }
        }
    }
}