package com.mashup.zuzu.data.source.remote.review

import com.mashup.zuzu.data.di.IoDispatcher
import com.mashup.zuzu.data.response.GetDrinksEvaluationResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

/**
 * @Created by 김현국 2022/08/23
 */
class ReviewDetailRemoteDataSource @Inject constructor(
    private val reviewApi: ReviewApi,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) {

    suspend fun getDrinksEvaluationWithId(wineId: Long): Response<GetDrinksEvaluationResponse> {
        return withContext(ioDispatcher) {
            return@withContext reviewApi.getDrinksEvaluationWithId(drinkId = wineId)
        }
    }
}
