package com.mashup.zuzu.data.source.remote.user

import com.mashup.zuzu.data.model.*
import kotlinx.coroutines.flow.Flow

/**
 * @Created by 김현국 2022/08/12
 */
interface UserRemoteDataSource {

    fun getUserData(userId: Long): Flow<Results<User>>

    fun getWineCallerList(userId: Long): Flow<Results<List<Wine>>>

    fun getJoinedWorldCupList(userId: Long): Flow<Results<List<BestWorldCup>>>

    fun updateUserProfile(): Flow<Results<String>>

    fun leaveMembership(userId: Long): Flow<Results<String>>
}
