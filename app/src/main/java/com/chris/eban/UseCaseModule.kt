package com.chris.eban

import com.chris.eban.domain.EventListRepository
import com.chris.eban.domain.JobThread
import com.chris.eban.domain.usecase.EventListQuery
import com.chris.eban.domain.usecase.EventSaveDelete
import com.chris.eban.domain.usecase.EventSaveInsert
import com.chris.eban.domain.usecase.EventSaveUpdate
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class UseCaseModule {


    //********************* 事件列表 ************************//
    @Provides
    @Singleton
    fun eventListQuery(jobThread: JobThread, repository: EventListRepository): EventListQuery {
        return EventListQuery(jobThread, repository)
    }

    @Provides
    @Singleton
    fun eventSave(jobThread: JobThread, repository: EventListRepository): EventSaveInsert {
        return EventSaveInsert(jobThread, repository)
    }

    @Provides
    @Singleton
    fun saveUpdate(jobThread: JobThread, repository: EventListRepository): EventSaveUpdate {
        return EventSaveUpdate(jobThread, repository)
    }

    @Provides
    @Singleton
    fun saveDelete(jobThread: JobThread, repository: EventListRepository): EventSaveDelete {
        return EventSaveDelete(jobThread, repository)
    }
}
