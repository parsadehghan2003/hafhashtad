package com.ftpd.hafhashtad.gateway.di

import com.ftpd.hafhashtad.base.Constants
import com.ftpd.hafhashtad.gateway.FakeApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.Interceptor.*
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideFakeApiService(
        okHttpClient: OkHttpClient,
        gsonConverter: GsonConverterFactory
    ): FakeApiService {
        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(gsonConverter)
            .client(okHttpClient)
            .build()
        return retrofit.create(FakeApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideGsonFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Provides
    @Singleton
    fun providesOkHttpClient(): OkHttpClient = OkHttpClient.Builder().build()
}