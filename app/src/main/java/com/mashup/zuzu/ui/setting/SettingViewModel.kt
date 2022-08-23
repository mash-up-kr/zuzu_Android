package com.mashup.zuzu.ui.setting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mashup.zuzu.ProofPreferenceImpl
import com.mashup.zuzu.bridge.ProofPreference
import com.mashup.zuzu.data.model.Results
import com.mashup.zuzu.domain.usecase.GetUserDataUseCase
import com.mashup.zuzu.ui.user.UserUiState
import com.mashup.zuzu.util.Key
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @Created by 김현국 2022/07/20
 */

@HiltViewModel
class SettingViewModel @Inject constructor(
    private val proofPreference: ProofPreference,
    private val getUserDataUseCase: GetUserDataUseCase
) : ViewModel() {

    private val _user: MutableStateFlow<UserUiState> = MutableStateFlow(UserUiState.Loading)
    val user = _user.asStateFlow()

    private val _logout: MutableStateFlow<LogoutUiState> = MutableStateFlow(LogoutUiState.Loading)
    val logout = _logout.asStateFlow()

    init {
        getUserData()
    }
    fun getUserData() {
        viewModelScope.launch {
            getUserDataUseCase().collect { result ->
                when (result) {
                    is Results.Loading -> {
                        _user.value = UserUiState.Loading
                    }
                    is Results.Failure -> {
                        _user.value = UserUiState.Error
                    }
                    is Results.Success -> {
                        _user.value = UserUiState.Success(result.value)
                    }
                }
            }
        }
    }

    fun logout() {
        viewModelScope.launch {
            with(proofPreference) {
                commit(Key.Preference.ACCESS_TOKEN, "")
                commit(Key.Preference.REFRESH_TOKEN, "")
            }
            _logout.value = LogoutUiState.Success
        }
    }
}
