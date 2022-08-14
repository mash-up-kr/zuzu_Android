package com.mashup.zuzu.data.di

import com.mashup.zuzu.data.repository.CategoryRepository
import com.mashup.zuzu.data.repository.HomeRepository
import com.mashup.zuzu.data.repository.ReviewDetailRepository
import com.mashup.zuzu.data.repository.ReviewWriteRepository
import com.mashup.zuzu.data.repository.UserRepository
import com.mashup.zuzu.data.source.remote.CategoryRemoteDataSource
import com.mashup.zuzu.data.source.remote.UserRemoteDataSource
import com.mashup.zuzu.data.source.remote.WineRemoteDataSource
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
    fun provideHomeRepository(wineRemoteDataSource: WineRemoteDataSource): HomeRepository {
        return HomeRepository(wineRemoteDataSource = wineRemoteDataSource)
    }

    @Singleton
    @Provides
    fun provideCategoryRepository(categoryRemoteDataSource: CategoryRemoteDataSource): CategoryRepository {
        return CategoryRepository(categoryRemoteDataSource = categoryRemoteDataSource)
    }

    @Singleton
    @Provides
    fun provideUserRepository(userRemoteDataSource: UserRemoteDataSource): UserRepository {
        return UserRepository(userRemoteDataSource = userRemoteDataSource)
    }

    @Singleton
    @Provides
    fun provideReviewDetailRepository(): ReviewDetailRepository {
        return ReviewDetailRepository()
    }
}
