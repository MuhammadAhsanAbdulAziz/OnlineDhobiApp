package com.example.onlinedhobi.di

import com.example.onlinedhobi.api.AuthApi
import com.example.onlinedhobi.api.AuthInterceptor
import com.example.onlinedhobi.api.UserApi
import com.example.onlinedhobi.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {
    @Singleton
    @Provides
    fun provideRetrofitBuilder(): Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(authInterceptor: AuthInterceptor): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(authInterceptor).build()
    }

    @Singleton
    @Provides
    fun provideAuthApi(retrofitBuilder: Retrofit.Builder): AuthApi {
        return retrofitBuilder
            .build()
            .create(AuthApi::class.java)
    }

    @Singleton
    @Provides
    fun provideUserApi(
        retrofitBuilder: Retrofit.Builder,
        okHttpClient: OkHttpClient
    ): UserApi {
        return retrofitBuilder
            .client(okHttpClient)
            .build()
            .create(UserApi::class.java)
    }
}