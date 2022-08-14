package com.mashup.zuzu.data.di

import com.mashup.zuzu.data.repository.*
import com.mashup.zuzu.data.source.remote.category.CategoryRemoteDataSource
import com.mashup.zuzu.data.source.remote.user.UserRemoteDataSource
import com.mashup.zuzu.data.source.remote.wine.WineRemoteDataSource
import com.mashup.zuzu.data.source.remote.worldcup.WorldCupRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
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
    fun provideHomeRepository(
        wineRemoteDataSource: WineRemoteDataSource,
        @IoDispatcher ioDispatcher: CoroutineDispatcher
    ): WineRepository {
        return WineRepository(
            wineRemoteDataSource = wineRemoteDataSource,
            ioDispatcher = ioDispatcher
        )
    }

    @Singleton
    @Provides
    fun provideCategoryRepository(
        categoryRemoteDataSource: CategoryRemoteDataSource,
        @IoDispatcher ioDispatcher: CoroutineDispatcher
    ): CategoryRepository {
        return CategoryRepository(
            categoryRemoteDataSource = categoryRemoteDataSource,
            ioDispatcher = ioDispatcher
        )
    }

    @Singleton
    @Provides
    fun provideUserRepository(
        userRemoteDataSource: UserRemoteDataSource,
        @IoDispatcher ioDispatcher: CoroutineDispatcher
    ): UserRepository {
        return UserRepository(
            userRemoteDataSource = userRemoteDataSource,
            ioDispatcher = ioDispatcher
        )
    }

    @Singleton
    @Provides
    fun provideReviewDetailRepository(): ReviewDetailRepository {
        return ReviewDetailRepository()
    }

    @Singleton
    @Provides
    fun provideWorldCupRepository(
        worldCupRemoteDataSource: WorldCupRemoteDataSource,
        @IoDispatcher ioDispatcher: CoroutineDispatcher
    ): WorldCupRepository {
        return WorldCupRepository(
            worldCupRemoteDataSource = worldCupRemoteDataSource,
            ioDispatcher = ioDispatcher
        )
    }
}
