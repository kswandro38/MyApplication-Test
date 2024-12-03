package com.kalanasarange.myapplication.di

import com.kalanasarange.myapplication.api.MyAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    /**
     * Retrofit provider provide response along with headers.
     * GSON has used as JSON to Obj convertor
     */
    @Provides
    fun provideRetrofit(): Retrofit {
        val okHttpClientBuilder = OkHttpClient.Builder()
        return Retrofit.Builder()
            .baseUrl(MyAPI.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClientBuilder.build())
            .build()
    }

    /**
     * Main API interface provider. This will inject the APIs to
     * all the repositories to full fill the api needs.
     */
    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit): MyAPI =
        retrofit.create(MyAPI::class.java)
}