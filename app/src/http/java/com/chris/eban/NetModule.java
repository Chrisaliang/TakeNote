package com.chris.eban;

import com.chris.eban.data.http.HttpLogger;
import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@SuppressWarnings("WeakerAccess")
@Module
public abstract class NetModule {

    @Singleton
    @Provides
    public static HttpLoggingInterceptor.Logger logger() {
        return new HttpLogger();
    }

    @Singleton
    @Provides
    public static HttpLoggingInterceptor loggingInterceptor(HttpLoggingInterceptor.Logger logger) {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(logger);
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return httpLoggingInterceptor;
    }

    @Singleton
    @Provides
    public static OkHttpClient provideOkHttpClient(HttpLoggingInterceptor interceptor) {
        return new OkHttpClient.Builder()
                .addNetworkInterceptor(interceptor)
                .build();
    }

    @Singleton
    @Provides
    public static Retrofit provideGankRetrofit(OkHttpClient client, Gson gson) {
        return new Retrofit.Builder()
                .baseUrl(ServiceEnvironmentConfig.BaseUrl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }
}
