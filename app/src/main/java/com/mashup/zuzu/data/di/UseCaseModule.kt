package com.mashup.zuzu.data.di

import com.mashup.zuzu.data.repository.CategoryRepository
import com.mashup.zuzu.data.repository.HomeRepository
import com.mashup.zuzu.data.repository.UserRepository
import com.mashup.zuzu.domain.usecase.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * @Created by 김현국 2022/07/24
 */
@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Provides
    @Singleton
    fun provideGetBestWorldCupListUseCase(homeRepository: HomeRepository): GetBestWorldCupListUseCase {
        return GetBestWorldCupListUseCase(repository = homeRepository)
    }

    @Provides
    @Singleton
    fun provideGetMainWineListUseCase(homeRepository: HomeRepository): GetMainWineListUseCase {
        return GetMainWineListUseCase(repository = homeRepository)
    }

    @Provides
    @Singleton
    fun provideGetRecommendWineUseCase(homeRepository: HomeRepository): GetRecommendWineUseCase {
        return GetRecommendWineUseCase(repository = homeRepository)
    }

    @Provides
    @Singleton
    fun provideJoinedWorldCupListUseCase(userRepository: UserRepository): GetJoinedWorldCupListUseCase {
        return GetJoinedWorldCupListUseCase(repository = userRepository)
    }

    @Provides
    @Singleton
    fun provideGetUserDataUseCase(userRepository: UserRepository): GetUserDataUseCase {
        return GetUserDataUseCase(repository = userRepository)
    }

    @Provides
    @Singleton
    fun provideGetWineCallerListUseCase(userRepository: UserRepository): GetWineCallerListUseCase {
        return GetWineCallerListUseCase(repository = userRepository)
    }

    @Provides
    @Singleton
    fun provideUpdateUserProfileUseCase(userRepository: UserRepository): UpdateUserProfileUseCase {
        return UpdateUserProfileUseCase(repository = userRepository)
    }

    @Provides
    @Singleton
    fun provideGetWineListWithCategoryUseCase(categoryRepository: CategoryRepository): GetWineListWithCategoryUseCase {
        return GetWineListWithCategoryUseCase(repository = categoryRepository)
    }

    @Provides
    @Singleton
    fun provideGetWineListUseCase(categoryRepository: CategoryRepository): GetWineListUseCase {
        return GetWineListUseCase(repository = categoryRepository)
    }

    @Provides
    @Singleton
    fun provideLeaveMembershipUseCase(userRepository: UserRepository): LeaveMembershipUseCase {
        return LeaveMembershipUseCase(repository = userRepository)
    }

    @Provides
    @Singleton
    fun provideGetCategoryListUseCase(categoryRepository: CategoryRepository): GetCategoryListUseCase {
        return GetCategoryListUseCase(repository = categoryRepository)
    }
}
