package com.mashup.zuzu.ui.user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mashup.zuzu.data.model.User
import com.mashup.zuzu.data.model.UserRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * @Created by 김현국 2022/07/18
 * @Time 1:31 오후
 */

sealed class UserUiState {
    object Loading : UserUiState()
    object Error : UserUiState()
    data class Success(val userData: User) : UserUiState()
    object NoItem : UserUiState()
}
class UserViewModel() : ViewModel() {

    private val _user: MutableStateFlow<UserUiState> =
        MutableStateFlow(UserUiState.Loading)
    val user = _user.asStateFlow()

    init {
        getUserData()
    }

    fun getUserData() {
        viewModelScope.launch {
            UserRepo.getUserData().collect { user ->
                _user.value = UserUiState.Success(userData = user)
            }
        }
    }

    fun getWineCallerData() {
    }

    fun getJoinWorldCupData() {
    }

    fun updateUserProfile(name: String) {
        _user.value.let { uiState ->
            when (uiState) {
                is UserUiState.Success -> {
                    uiState.userData.name = name
                } else -> {
                }
            }
        }
    }

    fun submitUserProfile(name: String) {
        UserRepo.submitUserProfile(username = name)
    }
}
