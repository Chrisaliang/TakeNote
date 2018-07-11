package com.chris.takenote;

import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public abstract class JsonModule {

    @Provides
    @Singleton
    public static Gson gson() {
        return new Gson();
    }
}
