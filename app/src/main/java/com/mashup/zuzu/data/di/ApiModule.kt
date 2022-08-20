package com.mashup.zuzu.data.di

import android.content.Context
import com.mashup.zuzu.BuildConfig
import com.mashup.zuzu.ProofPreferenceImpl
import com.mashup.zuzu.bridge.ProofPreference
import com.mashup.zuzu.data.source.remote.worldcup.WorldCupApi
import com.mashup.zuzu.data.source.remote.wine.WineApi
import com.mashup.zuzu.data.source.remote.category.CategoryApi
import com.mashup.zuzu.data.source.remote.review.ReviewApi
import com.mashup.zuzu.data.source.remote.user.UserApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * @Created by 김현국 2022/08/08
 */

@Module
@InstallIn(SingletonComponent::class)
class ApiModule {

    @Provides
    @Singleton
    fun provideCategoryRemoteApi(retrofit: Retrofit): CategoryApi {
        return retrofit.create(CategoryApi::class.java)
    }

    @Provides
    @Singleton
    fun provideUserRemoteApi(retrofit: Retrofit): UserApi {
        return retrofit.create(UserApi::class.java)
    }

    @Provides
    @Singleton
    fun provideWineRemoteApi(retrofit: Retrofit): WineApi {
        return retrofit.create(WineApi::class.java)
    }

    @Provides
    @Singleton
    fun provideWorldCupRemoteApi(retrofit: Retrofit): WorldCupApi {
        return retrofit.create(WorldCupApi::class.java)
    }

    @Provides
    @Singleton
    fun provideReviewRemoteApi(retrofit: Retrofit): ReviewApi {
        return retrofit.create(ReviewApi::class.java)
    }


    @Singleton
    @Provides
    fun provideWebPreference(@ApplicationContext context: Context): ProofPreference {
        return ProofPreferenceImpl(context, BuildConfig.APPLICATION_ID)
    }
}
