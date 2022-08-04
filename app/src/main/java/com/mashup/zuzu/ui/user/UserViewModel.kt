package com.mashup.zuzu.ui.user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mashup.zuzu.data.model.Results
import com.mashup.zuzu.domain.usecase.GetJoinedWorldCupListUseCase
import com.mashup.zuzu.domain.usecase.GetUserDataUseCase
import com.mashup.zuzu.domain.usecase.GetWineCallerListUseCase
import com.mashup.zuzu.domain.usecase.UpdateUserProfileUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @Created by 김현국 2022/07/18
 */

@HiltViewModel
class UserViewModel @Inject constructor(
    private val getUserDataUseCase: GetUserDataUseCase,
    private val getJoinedWorldCupListUseCase: GetJoinedWorldCupListUseCase,
    private val getWineCallerListUseCase: GetWineCallerListUseCase,
    private val updateUserProfileUseCase: UpdateUserProfileUseCase
) : ViewModel() {

    private val _user: MutableStateFlow<UserUiState> = MutableStateFlow(UserUiState.Loading)
    val user = _user.asStateFlow()

    private val _wineCaller: MutableStateFlow<WineCallerUiState> = MutableStateFlow(WineCallerUiState.Loading)
    val wineCaller = _wineCaller.asStateFlow()

    private val _joinedWorldCup: MutableStateFlow<JoinedWorldCupUiState> = MutableStateFlow(JoinedWorldCupUiState.Loading)
    val joinedWorldCup = _joinedWorldCup.asStateFlow()

    private val _submit: MutableStateFlow<UpdateProfileUiEventState> = MutableStateFlow(UpdateProfileUiEventState.Init)
    val submit = _submit.asStateFlow()

    init {
        getUserData(userId = 0L) // userId 수정
        getWineCallerData(userId = 0L)
        getJoinWorldCupData(userId = 0L)
    }

    private fun getUserData(userId: Long) {
        viewModelScope.launch {
            getUserDataUseCase(userId = userId).collect { result ->
                when (result) {
                    is Results.Success -> {
                        _user.value = UserUiState.Success(result.value)
                    }
                    is Results.Failure -> {
                    }
                    is Results.Loading -> {
                        _user.value = UserUiState.Loading
                    }
                }
            }
        }
    }

    fun getWineCallerData(userId: Long) {
        viewModelScope.launch {
            getWineCallerListUseCase(userId = userId).collect { result ->
                when (result) {
                    is Results.Success -> {
                        if (result.value.isEmpty()) {
                            _wineCaller.value = WineCallerUiState.NoItem
                        } else {
                            _wineCaller.value = WineCallerUiState.Success(result.value)
                        }
                    }
                    is Results.Loading -> {
                        _wineCaller.value = WineCallerUiState.Loading
                    }
                    is Results.Failure -> {
                        _wineCaller.value = WineCallerUiState.Error
                    }
                }
            }
        }
    }

    fun getJoinWorldCupData(userId: Long) {
        viewModelScope.launch {
            getJoinedWorldCupListUseCase(userId = userId).collect { result ->
                when (result) {
                    is Results.Success -> {
                        if (result.value.isEmpty()) {
                            _joinedWorldCup.value = JoinedWorldCupUiState.NoItem
                        } else {
                            _joinedWorldCup.value = JoinedWorldCupUiState.Success(result.value)
                        }
                    }
                    is Results.Loading -> {
                        _joinedWorldCup.value = JoinedWorldCupUiState.Loading
                    }
                    is Results.Failure -> {
                        _joinedWorldCup.value = JoinedWorldCupUiState.Error
                    }
                }
            }
        }
    }

    fun submitUserProfile(name: String) {
        viewModelScope.launch {
            updateUserProfileUseCase(profileName = name).collect { result ->
                when (result) {
                    is Results.Success -> {
                        _submit.value = UpdateProfileUiEventState.Success
                    }
                    is Results.Loading -> {
                        _submit.value = UpdateProfileUiEventState.Loading
                    }
                    is Results.Failure -> {
                        _submit.value = UpdateProfileUiEventState.Error
                    }
                }
            }
        }
    }
}
