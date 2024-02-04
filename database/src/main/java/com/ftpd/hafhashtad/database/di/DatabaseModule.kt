package com.ftpd.hafhashtad.database.di

import android.content.Context
import com.ftpd.hafhashtad.database.SingleRunner
import com.ftpd.hafhashtad.database.data_source.service.PostDataStorage
import com.ftpd.hafhashtad.database.domain.RealmPost
import com.ftpd.hafhashtad.database.domain.RealmPostRate
import com.ftpd.hafhashtad.database.framework.DatabaseQueue
import com.ftpd.hafhashtad.database.framework.PostDataStorageImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.realm.kotlin.RealmConfiguration
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExecutorCoroutineDispatcher
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.plus
import java.util.concurrent.Executors
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabaseScope(): CoroutineScope = GlobalScope + Dispatchers.IO

    @Provides
    @Singleton
    fun provideDatabaseCoroutineQueue(): ExecutorCoroutineDispatcher =
        Executors.newSingleThreadExecutor().asCoroutineDispatcher()

    @Provides
    @Singleton
    fun provideSingleRunning(): SingleRunner =
        SingleRunner()

    @Provides
    @Singleton
    fun provideRealmConfig(): RealmConfiguration {
        return RealmConfiguration.Builder(setOf(RealmPost::class, RealmPostRate::class))
            .name("Hafhashtad")
            .schemaVersion(0)
            .build()
    }

    @Provides
    @Singleton
    fun providePostDataStorage(
        databaseQueue: DatabaseQueue
    ): PostDataStorage =
        PostDataStorageImpl(databaseQueue)

    @Provides
    @Singleton
    fun provideDatabaseQueue(
        databaseScope: CoroutineScope,
        databaseQueue: ExecutorCoroutineDispatcher,
        realmConfiguration: RealmConfiguration,
    ): DatabaseQueue = DatabaseQueue(databaseScope, databaseQueue, realmConfiguration)


}