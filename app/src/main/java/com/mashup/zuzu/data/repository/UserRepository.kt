package com.mashup.zuzu.data.repository

import com.mashup.zuzu.data.mapper.reviewsDrinksResponseToModel
import com.mashup.zuzu.data.mapper.userProfileImagesResponseToModel
import com.mashup.zuzu.data.mapper.userResponseToModel
import com.mashup.zuzu.data.model.*
import com.mashup.zuzu.data.model.dummy.DummyRepo
import com.mashup.zuzu.data.source.remote.user.UserRemoteDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

/**
 * @Created by 김현국 2022/07/24
 */
class UserRepository constructor(
    private val userRemoteDataSource: UserRemoteDataSource,
    private val ioDispatcher: CoroutineDispatcher
) {
    fun getUserData(): Flow<Results<User>> {
        return flow {
            emit(Results.Loading)
            val response = userRemoteDataSource.getUserData()
            val data = response.body()
            if (response.isSuccessful && data != null) {
                emit(Results.Success(userResponseToModel(data)))
            }
        }.flowOn(ioDispatcher)
    }

    fun updateUserProfile(userName: String, index: Long): Flow<Results<String>> {
        return flow {
            emit(Results.Loading)
            val response = userRemoteDataSource.updateUser(nickname = userName, profileId = index)
            if (response.isSuccessful) {
                emit(Results.Success("완료헀습니다"))
            }
            emit(Results.Success("완료했습니다"))
        }.flowOn(ioDispatcher)
    }

    fun getUserProfileImages(): Flow<Results<List<UserProfileImages>>> {
        return flow {
            emit(Results.Loading)
            val response = userRemoteDataSource.getUserProfileImages()
            val body = response.body()
            if (response.isSuccessful && body != null) {
                val data = userProfileImagesResponseToModel(body)
                emit(Results.Success(data))
            }
        }
    }

    fun deleteUser(): Flow<Results<String>> {
        return flow {
            emit(Results.Loading)
            val response = userRemoteDataSource.deleteUser()
            if (response.isSuccessful) {
                emit(Results.Success("탍퇴 성공"))
            }
        }.flowOn(ioDispatcher)
    }

    fun getReviewsDrinks(drinkId: Long): Flow<Results<ReviewShareCards>> {
        return flow {
            emit(Results.Loading)
            val response = userRemoteDataSource.getReviewsDrinks(
                drinkId = drinkId
            )
            val body = response.body()
            if (response.isSuccessful && body != null) {
                val data = reviewsDrinksResponseToModel(body)
                emit(Results.Success(data))
            }

        }.flowOn(ioDispatcher)
    }
}
