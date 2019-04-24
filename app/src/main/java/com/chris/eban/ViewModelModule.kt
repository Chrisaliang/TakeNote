package com.chris.eban

import androidx.lifecycle.ViewModelProvider
import com.chris.eban.common.EBanViewModelProviderFactory
import com.chris.eban.domain.usecase.EventListQuery
import com.chris.eban.domain.usecase.EventSaveDelete
import com.chris.eban.domain.usecase.EventSaveInsert
import com.chris.eban.domain.usecase.EventSaveUpdate
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ViewModelModule {

    @Singleton
    @Provides
    fun providerFactory(
            eventListQuery: EventListQuery,
            eventSaveInsert: EventSaveInsert,
            eventSaveUpdate: EventSaveUpdate,
            eventSaveDelete: EventSaveDelete
    ): ViewModelProvider.Factory {
        return EBanViewModelProviderFactory(
                eventListQuery,
                eventSaveInsert,
                eventSaveUpdate,
                eventSaveDelete
        )
    }
}
