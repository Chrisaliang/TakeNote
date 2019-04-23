package com.chris.eban

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
internal object JsonModule {

    private val DATE_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSSZ2"

    @Provides
    @Singleton
    fun gson(): Gson {
        return GsonBuilder()
                .setDateFormat(DATE_PATTERN)
                .create()
    }
}
