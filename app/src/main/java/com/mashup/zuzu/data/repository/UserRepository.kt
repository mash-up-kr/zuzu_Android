package com.mashup.zuzu.data.repository

import com.mashup.zuzu.data.model.*
import com.mashup.zuzu.data.source.remote.user.UserApi
import com.mashup.zuzu.data.source.remote.user.UserRemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

/**
 * @Created by 김현국 2022/07/24
 */
class UserRepository @Inject constructor(
    private val userApi: UserApi
) : UserRemoteDataSource {
    override fun getUserData(userId: Long): Flow<Results<User>> {
        return flow {
            emit(Results.Loading)
            delay(1000)
            emit(Results.Success(user))
        }.flowOn(Dispatchers.IO)
    }

    override fun getWineCallerList(userId: Long): Flow<Results<List<Wine>>> {
        return flow {
            emit(Results.Loading)
            delay(1000)
            emit(Results.Success(wines))
        }.flowOn(Dispatchers.IO)
    }

    override fun getJoinedWorldCupList(userId: Long): Flow<Results<List<BestWorldCup>>> {
        return flow {
            emit(Results.Loading)
            delay(1000)
            emit(Results.Success(bestWorldCupList))
        }.flowOn(Dispatchers.IO)
    }

    override fun updateUserProfile(): Flow<Results<String>> {
        return flow {
            emit(Results.Loading)
            delay(1000)
            emit(Results.Success("완료했습니다"))
        }.flowOn(Dispatchers.IO)
    }

    override fun leaveMembership(userId: Long): Flow<Results<String>> {
        return flow {
            emit(Results.Loading)
            delay(1000)
            emit(Results.Success("탈퇴 성공"))
        }.flowOn(Dispatchers.IO)
    }
}
