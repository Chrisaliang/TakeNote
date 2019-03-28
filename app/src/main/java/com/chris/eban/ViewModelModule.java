package com.chris.eban;

import com.chris.eban.common.EBanViewModelProviderFactory;
import com.chris.eban.domain.usecase.EventListQuery;
import com.chris.eban.domain.usecase.EventSaveInsert;
import com.chris.eban.domain.usecase.EventSaveUpdate;

import javax.inject.Singleton;

import androidx.lifecycle.ViewModelProvider;
import dagger.Module;
import dagger.Provides;

@Module
class ViewModelModule {

    @Singleton
    @Provides
    static ViewModelProvider.Factory providerFactory(EventListQuery eventListQuery,
                                                     EventSaveInsert eventSaveInsert,
                                                     EventSaveUpdate eventSaveUpdate) {
        return new EBanViewModelProviderFactory(eventListQuery, eventSaveInsert, eventSaveUpdate);
    }
}
