package com.mashup.zuzu.data.di

import com.mashup.zuzu.data.source.remote.CategoryRemoteDataSource
import com.mashup.zuzu.data.source.remote.UserRemoteDataSource
import com.mashup.zuzu.data.source.remote.WineRemoteDataSource
import com.mashup.zuzu.data.source.remote.WorldCupRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
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
    fun provideCategoryRemoteDataSource(retrofit: Retrofit): CategoryRemoteDataSource {
        return retrofit.create(CategoryRemoteDataSource::class.java)
    }

    @Provides
    @Singleton
    fun provideUserRemoteDataSource(retrofit: Retrofit): UserRemoteDataSource {
        return retrofit.create(UserRemoteDataSource::class.java)
    }

    @Provides
    @Singleton
    fun provideWineRemoteDataSource(retrofit: Retrofit): WineRemoteDataSource {
        return retrofit.create(WineRemoteDataSource::class.java)
    }

    @Provides
    @Singleton
    fun provideWorldCupRemoteDataSource(retrofit: Retrofit): WorldCupRemoteDataSource {
        return retrofit.create(WorldCupRemoteDataSource::class.java)
    }
}
