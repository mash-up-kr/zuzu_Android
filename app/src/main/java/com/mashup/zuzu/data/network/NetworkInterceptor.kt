package com.mashup.zuzu.data.network

import com.mashup.zuzu.bridge.ProofPreference
import okhttp3.Interceptor
import okhttp3.Response
import timber.log.Timber
import javax.inject.Inject

/**
 * @Created by 김현국 2022/08/08
 */
class NetworkInterceptor @Inject constructor(
    private val proofPreference: ProofPreference
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val accessToken = proofPreference.get("accessToken", "")

        return chain.proceed(
            request = chain.request().newBuilder().apply {
                header("accept" , "application/json")
                header("Authorization", "Bearer $accessToken") // token
            }
                .build()
        )
    }
}
