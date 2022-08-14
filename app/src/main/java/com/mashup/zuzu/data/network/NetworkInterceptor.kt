package com.mashup.zuzu.data.network

import okhttp3.Interceptor
import okhttp3.Response

/**
 * @Created by 김현국 2022/08/08
 */
class NetworkInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(
            request = chain.request().newBuilder().apply {
                header("accept", "*/*") // token
            }
                .build()
        )
    }
}
