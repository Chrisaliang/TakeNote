package com.chris.eban

import com.chris.eban.data.http.HttpLogger
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetModule {

    @Singleton
    @Provides
    fun logger(): HttpLoggingInterceptor.Logger {
        return HttpLogger()
    }

    @Singleton
    @Provides
    fun loggingInterceptor(logger: HttpLoggingInterceptor.Logger): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor(logger)
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
                .addNetworkInterceptor(interceptor)
                .build()
    }

    @Singleton
    @Provides
    fun provideGankRetrofit(client: OkHttpClient, gson: Gson): Retrofit {
        return Retrofit.Builder()
                .baseUrl(ServiceEnvironmentConfig.BaseUrl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
    }
}
