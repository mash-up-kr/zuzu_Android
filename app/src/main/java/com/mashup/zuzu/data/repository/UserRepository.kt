package com.mashup.zuzu.data.repository

import com.mashup.zuzu.data.model.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * @Created by 김현국 2022/07/24
 */
class UserRepository @Inject constructor() {
    fun getUserData(userId: Long): Flow<Results<User>> {
        return flow {
            emit(Results.Loading)
            delay(1000)
            emit(Results.Success(user))
        }
    }

    fun getWineCallerList(userId: Long): Flow<Results<List<Wine>>> {
        return flow {
            emit(Results.Loading)
            delay(1000)
            emit(Results.Success(wines))
        }
    }

    fun getJoinedWorldCupList(userId: Long): Flow<Results<List<BestWorldCup>>> {
        return flow {
            emit(Results.Loading)
            delay(1000)
            emit(Results.Success(bestWorldCupList))
        }
    }

    fun updateUserProfile(): Flow<Results<String>> {
        return flow {
            emit(Results.Loading)
            delay(1000)
            emit(Results.Success("완료했습니다"))
        }
    }

    fun leaveMembership(userId: Long): Flow<Results<String>> {
        return flow {
            emit(Results.Loading)
            delay(1000)
            emit(Results.Success("탈퇴 성공"))
        }
    }
}
