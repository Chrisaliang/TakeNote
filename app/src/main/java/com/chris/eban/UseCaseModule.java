package com.chris.eban;

import com.chris.eban.domain.EventListRepository;
import com.chris.eban.domain.JobThread;
import com.chris.eban.domain.usecase.EventListQuery;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
abstract class UseCaseModule {


    //********************* 事件列表 ************************//
    @Provides
    @Singleton
    static EventListQuery eventListQuery(JobThread jobThread, EventListRepository repository) {
        return new EventListQuery(jobThread, repository);
    }
}