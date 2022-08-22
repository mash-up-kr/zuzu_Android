package com.mashup.zuzu.ui.user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mashup.zuzu.data.mapper.joinWorldListResponseToUiModel
import com.mashup.zuzu.data.model.Results
import com.mashup.zuzu.domain.usecase.*
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
    private val getWineCallerListUseCase: GetDrinksReviewsInUserPage,
    private val updateUserProfileUseCase: UpdateUserProfileUseCase,
    private val getUserProfileImagesUseCase: GetUserProfileImagesUseCase
) : ViewModel() {

    private val _user: MutableStateFlow<UserUiState> = MutableStateFlow(UserUiState.Loading)
    val user = _user.asStateFlow()

    private val _wineCaller: MutableStateFlow<WineCallerUiState> = MutableStateFlow(WineCallerUiState.Loading)
    val wineCaller = _wineCaller.asStateFlow()

    private val _joinedWorldCup: MutableStateFlow<JoinedWorldCupUiState> = MutableStateFlow(JoinedWorldCupUiState.Loading)
    val joinedWorldCup = _joinedWorldCup.asStateFlow()

    private val _submit: MutableStateFlow<UpdateProfileUiEventState> = MutableStateFlow(UpdateProfileUiEventState.Init)
    val submit = _submit.asStateFlow()

    private val _userProfileImages: MutableStateFlow<UserProfileImagesUiState> = MutableStateFlow(UserProfileImagesUiState.Loading)
    val userProfileImages = _userProfileImages.asStateFlow()

    private val _userImageUrl = MutableStateFlow("")

    init {
        getUserData()
        getWineCallerData()
        getJoinWorldCupData()
    }

    fun getUserData() {
        viewModelScope.launch {
            getUserDataUseCase().collect { result ->
                when (result) {
                    is Results.Success -> {
                        _userImageUrl.value = result.value.image
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

    fun getWineCallerData() {
        viewModelScope.launch {
            getWineCallerListUseCase().collect { result ->
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

    fun getJoinWorldCupData() {
        viewModelScope.launch {
            getJoinedWorldCupListUseCase().collect { result ->
                when (result) {
                    is Results.Success -> {
                        if (result.value.isEmpty()) {
                            _joinedWorldCup.value = JoinedWorldCupUiState.NoItem
                        } else {
                            val data = joinWorldListResponseToUiModel(result.value)
                            _joinedWorldCup.value = JoinedWorldCupUiState.Success(data)
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

    fun submitUserProfile(name: String, index: Long) {
        viewModelScope.launch {
            updateUserProfileUseCase(profileName = name, index = index).collect { result ->
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

    fun getUserProfileImages() {
        viewModelScope.launch {
            getUserProfileImagesUseCase().collect { result ->
                when (result) {
                    is Results.Success -> {
                        val idx = result.value.withIndex().filter {
                            it.value.image_url == _userImageUrl.value
                        }.map {
                            it.index
                        }
                        val currentProfile = result.value[idx[0]] // 현재의 유저의 이미지
                        val newResult = result.value.toMutableList()
                        newResult.removeAt(idx[0])
                        newResult.add(0, currentProfile)
                        _userProfileImages.value = UserProfileImagesUiState.Success(newResult)
                    }
                    is Results.Loading -> {
                    }
                    is Results.Failure -> {
                    }
                }
            }
        }
    }
}
