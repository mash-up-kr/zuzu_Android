package com.mashup.zuzu.ui.user

import com.mashup.zuzu.data.model.*
import com.mashup.zuzu.ui.model.BestWorldCup

/**
 * @Created by 김현국 2022/08/01
 */

sealed class UserUiState {
    object Loading : UserUiState()
    object Error : UserUiState()
    data class Success(val userData: User) : UserUiState()
}
sealed class WineCallerUiState {
    object Loading : WineCallerUiState()
    object Error : WineCallerUiState()
    data class Success(val wineCaller: List<Wine>) : WineCallerUiState()
    object NoItem : WineCallerUiState()
}
sealed class JoinedWorldCupUiState {
    object Loading : JoinedWorldCupUiState()
    object Error : JoinedWorldCupUiState()
    data class Success(val joinedWorldCupList: List<BestWorldCup>) : JoinedWorldCupUiState()
    object NoItem : JoinedWorldCupUiState()
}

sealed class UpdateProfileUiEventState {
    object Loading : UpdateProfileUiEventState()
    object Error : UpdateProfileUiEventState()
    object Success : UpdateProfileUiEventState()
    object Init : UpdateProfileUiEventState()
}

sealed class UserProfileImagesUiState {
    object Loading : UserProfileImagesUiState()
    data class Success(val userProfileImages: List<UserProfileImages>) : UserProfileImagesUiState()
}
