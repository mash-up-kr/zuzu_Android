package com.mashup.zuzu.data.network

import com.mashup.zuzu.bridge.ProofPreference
import okhttp3.Interceptor
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody
import java.io.IOException
import java.net.UnknownHostException
import javax.inject.Inject

/**
 * @Created by 김현국 2022/08/08
 */
class NetworkInterceptor @Inject constructor(
    private val proofPreference: ProofPreference
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val accessToken = proofPreference.get("accessToken", "")
        val request = chain.request()

        try {
            return chain.proceed(
                request = request.newBuilder().apply {
                    header("accept", "application/json")
                    header("Authorization", "Bearer $accessToken") // token
                }
                    .build()
            )
        } catch (e: Exception) {
            when (e) {
                is UnknownHostException -> {
                }
                is IOException -> {
                }
                is IllegalStateException -> {
                }
                else -> {
                }
            }
            return Response.Builder()
                .protocol(Protocol.HTTP_1_1)
                .request(request)
                .message("")
                .code(503)
                .body(ResponseBody.create(null, "$e"))
                .build()
        }
    }
}
