package com.mashup.zuzu.data.di

import com.mashup.zuzu.data.repository.CategoryRepository
import com.mashup.zuzu.data.repository.HomeRepository
import com.mashup.zuzu.data.repository.ReviewDetailRepository
import com.mashup.zuzu.data.repository.ReviewWriteRepository
import com.mashup.zuzu.data.repository.UserRepository
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
    fun provideReviewWriteRepository(): ReviewWriteRepository {
        return ReviewWriteRepository()
    }

    @Singleton
    @Provides
    fun provideHomeRepository(): HomeRepository {
        return HomeRepository()
    }

    @Singleton
    @Provides
    fun provideCategoryRepository(): CategoryRepository {
        return CategoryRepository()
    }

    @Singleton
    @Provides
    fun provideUserRepository(): UserRepository {
        return UserRepository()
    }

    fun provideReviewDetailRepository() : ReviewDetailRepository {
        return ReviewDetailRepository()
    }
}
