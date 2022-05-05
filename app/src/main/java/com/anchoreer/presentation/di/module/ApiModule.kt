package com.anchoreer.presentation.di.module

import android.content.Context
import com.anchoreer.data.api.MovieApiService
import com.anchoreer.presentation.R
import com.anchoreer.presentation.utils.BASE_API_URL
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Named("base_url")
    @Provides
    fun provideBaseUrl() = BASE_API_URL

    @Singleton
    @Provides
    fun provideOkHttpClient(@ApplicationContext appContext: Context) : OkHttpClient {
        return OkHttpClient().newBuilder()
            .apply {
                addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                })
                addInterceptor { chain ->
                    val naverClientId = appContext.getString(R.string.naver_client_id)
                    val naverClientSecret = appContext.getString(R.string.naver_client_secret)
                    val requestBuilder = chain.request().newBuilder()
                        .addHeader(
                            "X-Naver-Client-Id",
                            naverClientId
                        )
                        .addHeader(
                            "X-Naver-Client-Secret",naverClientSecret
                        )
                    chain.proceed(requestBuilder.build())
                }
            }
            .build()
    }

    @Singleton
    @Provides
    fun provideGSonBuilder() : Gson {
        return GsonBuilder()
            .apply {
                setLenient()
            }.create()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient,  @Named("base_url") baseUrl: String ,gson: Gson) : Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }


    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): MovieApiService {
        return retrofit.create(MovieApiService::class.java)
    }
}