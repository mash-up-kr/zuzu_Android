package com.mashup.zuzu.data.repository

import com.mashup.zuzu.data.mapper.userProfileImagesResponseToModel
import com.mashup.zuzu.data.mapper.userResponseToModel
import com.mashup.zuzu.data.model.*
import com.mashup.zuzu.data.source.remote.user.UserRemoteDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

/**
 * @Created by 김현국 2022/07/24
 */
class UserRepository @Inject constructor(
    private val userRemoteDataSource: UserRemoteDataSource,
    private val ioDispatcher: CoroutineDispatcher
) {
    fun getUserData(userId: Long): Flow<Results<User>> {
        return flow {
            emit(Results.Loading)
            val response = userRemoteDataSource.getUserData(userId = userId)
            val data = response.body()
            if (response.isSuccessful && data != null) {
                emit(Results.Success(userResponseToModel(data)))
            }
            emit(Results.Success(user))
        }.flowOn(ioDispatcher)
    }

    fun updateUserProfile(): Flow<Results<String>> {
        return flow {
            emit(Results.Loading)
            delay(1000)
            emit(Results.Success("완료했습니다"))
        }.flowOn(ioDispatcher)
    }

    fun getUserProfileImages(): Flow<Results<List<UserProfileImagesModel>>> {
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
}
