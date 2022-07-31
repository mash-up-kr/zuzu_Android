package com.mashup.zuzu.data.di

import com.mashup.zuzu.data.repository.ReviewDetailRepository
import com.mashup.zuzu.data.repository.ReviewWriteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideReviewWriteRepository() : ReviewWriteRepository {
        return ReviewWriteRepository()
    }

    @Singleton
    @Provides
    fun provideReviewDetailRepository() : ReviewDetailRepository {
        return ReviewDetailRepository()
    }
}