package com.chris.eban

import com.chris.eban.data.EventDao
import com.chris.eban.data.EventListRepositoryImpl
import com.chris.eban.domain.EventListRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
internal object RepositoryModule {

    @Provides
    @Singleton
    fun provideEventListRepository(eventDao: EventDao): EventListRepository {
        return EventListRepositoryImpl(eventDao)
    }
}
