package com.mashup.zuzu.data.di

import com.mashup.zuzu.BuildConfig
import com.mashup.zuzu.bridge.ProofPreference
import com.mashup.zuzu.data.network.NetworkInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * @Created by 김현국 2022/08/08
 */

@Module
@InstallIn(SingletonComponent::class)
class NetWorkModule {

    private val URL = "http://server.mashup-proof.click"

    @Provides
    @Singleton
    fun provideNetworkInterceptor(proofPreference: ProofPreference): NetworkInterceptor {
        return NetworkInterceptor(proofPreference = proofPreference)
    }

    @Provides
    @Singleton
    fun provideOkHttp3Client(networkInterceptor: NetworkInterceptor): OkHttpClient {
        val okHttpClientBuilder = OkHttpClient.Builder().addInterceptor(
            networkInterceptor
        )
        if (BuildConfig.DEBUG) {
            okHttpClientBuilder.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        }
        return okHttpClientBuilder.build()
    }

    @Provides
    @Singleton
    fun provideGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient, gsonConverterFactory: GsonConverterFactory): Retrofit {
        return Retrofit.Builder()
            .baseUrl(URL)
            .client(client)
            .addConverterFactory(gsonConverterFactory)
            .build()
    }
}
