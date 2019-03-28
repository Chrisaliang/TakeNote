package com.chris.eban;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
abstract class JsonModule {

private static final String DATE_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSSZ2";

    @Provides
    @Singleton
    static Gson gson() {
        return new GsonBuilder()
                .setDateFormat(DATE_PATTERN)
                .create();
    }
}
