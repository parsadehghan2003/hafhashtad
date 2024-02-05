package com.ftpd.post.framework.di

import com.ftpd.post.framework.PostLocalServiceImpl
import com.ftpd.post.framework.PostRemoteServiceImpl
import com.ftpd.hafhashtad.post.data_source.service.PostLocalService
import com.ftpd.hafhashtad.post.data_source.service.PostRemoteService
import com.ftpd.hafhashtad.database.data_source.service.PostDataStorage
import com.ftpd.hafhashtad.gateway.FakeApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped


@Module
@InstallIn(ViewModelComponent::class)
object PlaceFrameWorkModule {

    @Provides
    @ViewModelScoped
    fun providePlaceLocalService(
        postDataStorage: PostDataStorage,
    ): PostLocalService {
        return PostLocalServiceImpl(postDataStorage)
    }

    @Provides
    @ViewModelScoped
    fun providePlaceRemoteService(fakeApiService: FakeApiService): PostRemoteService {
        return PostRemoteServiceImpl(fakeApiService)
    }

}