package com.chris.eban;

import com.chris.eban.data.EventDao;
import com.chris.eban.data.EventListRepositoryImpl;
import com.chris.eban.domain.EventListRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
abstract class RepositoryModule {

    @Provides
    @Singleton
    static EventListRepository provideEventListRepository(EventDao eventDao) {
        return new EventListRepositoryImpl(eventDao);
    }
}
