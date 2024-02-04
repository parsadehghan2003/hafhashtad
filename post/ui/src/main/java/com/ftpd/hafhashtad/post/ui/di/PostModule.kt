package com.ftpd.hafhashtad.post.ui.di

import com.ftpd.hafhashtad.post.data_source.repository.PostRepository
import com.ftpd.hafhashtad.post.data_source.repository.PostRepositoryImpl
import com.ftpd.hafhashtad.post.data_source.service.PostLocalService
import com.ftpd.hafhashtad.post.data_source.service.PostRemoteService
import com.ftpd.post.usecase.GetPostsInteractor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped


@Module
@InstallIn(ViewModelComponent::class)
object PostModule {
    @Provides
    @ViewModelScoped
    fun providePlaceRepository(
        postLocalService: PostLocalService,
        postRemoteService: PostRemoteService
    ): PostRepository {
        return PostRepositoryImpl(postLocalService, postRemoteService)
    }
    @Provides
    @ViewModelScoped
    fun provideGetPostsInteractor(
        placeRepository: PostRepository
    ): GetPostsInteractor {
        return GetPostsInteractor(placeRepository)
    }
}