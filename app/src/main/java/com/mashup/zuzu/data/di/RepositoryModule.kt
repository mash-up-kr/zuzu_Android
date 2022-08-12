package com.mashup.zuzu.data.di

import com.mashup.zuzu.data.repository.CategoryRepository
import com.mashup.zuzu.data.repository.HomeRepository
import com.mashup.zuzu.data.repository.ReviewDetailRepository
import com.mashup.zuzu.data.repository.ReviewWriteRepository
import com.mashup.zuzu.data.repository.UserRepository
import com.mashup.zuzu.data.source.remote.category.CategoryApi
import com.mashup.zuzu.data.source.remote.category.CategoryRemoteDataSource
import com.mashup.zuzu.data.source.remote.user.UserApi
import com.mashup.zuzu.data.source.remote.user.UserRemoteDataSource
import com.mashup.zuzu.data.source.remote.wine.WineApi
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
    fun provideHomeRepository(wineApi: WineApi): HomeRepository {
        return HomeRepository(wineApi = wineApi)
    }

    @Singleton
    @Provides
    fun provideCategoryRepository(categoryApi: CategoryApi): CategoryRepository {
        return CategoryRepository(categoryApi = categoryApi)
    }

    @Singleton
    @Provides
    fun provideUserRepository(userApi: UserApi): UserRepository {
        return UserRepository(userApi = userApi)
    }

    @Singleton
    @Provides
    fun provideReviewDetailRepository(): ReviewDetailRepository {
        return ReviewDetailRepository()
    }
}
