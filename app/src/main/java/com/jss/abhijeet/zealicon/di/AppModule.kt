package com.jss.abhijeet.zealicon.di

import android.app.Application
import android.content.Context
import com.jss.abhijeet.zealicon.network.ApiService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class AppModule(internal var application: Application) {

    @Provides
    @Singleton
    fun provideContext(app: Application): Context = app.applicationContext

    @Provides
    @Singleton
    internal fun providesApplication(): Application = application

    @Singleton
    @Provides
    internal fun providesRetrofit(okHttpClient: OkHttpClient) =
            Retrofit.Builder()
                    .baseUrl("http://register.zealicon.in/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(okHttpClient)
                    .build()

    @Singleton
    @Provides
    fun providesOkHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        return OkHttpClient.Builder()
                .addInterceptor(logging)
                //.addNetworkInterceptor(StethoInterceptor())
                .build()
    }


    @Singleton
    @Provides
    internal fun providesApiService(retrofit: Retrofit) =
            retrofit.create(ApiService::class.java)

}