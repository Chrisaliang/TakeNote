package com.chris.eban.common;

import com.chris.eban.domain.usecase.EventListQuery;
import com.chris.eban.domain.usecase.EventSaveInsert;
import com.chris.eban.domain.usecase.EventSaveUpdate;
import com.chris.eban.presenter.event.EventDetailViewModel;
import com.chris.eban.presenter.event.EventListViewModel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class EBanViewModelProviderFactory implements ViewModelProvider.Factory {

    private EventListQuery eventListQuery;
    private EventSaveInsert eventSaveInsert;
    private EventSaveUpdate eventSaveUpdate;

    public EBanViewModelProviderFactory(EventListQuery eventListQuery,
                                        EventSaveInsert eventSaveInsert,
                                        EventSaveUpdate eventSaveUpdate) {
        this.eventListQuery = eventListQuery;
        this.eventSaveInsert = eventSaveInsert;
        this.eventSaveUpdate = eventSaveUpdate;
    }

    @SuppressWarnings("unchecked")
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(EventListViewModel.class)) {
            return (T) new EventListViewModel(eventListQuery);
        } else if (modelClass.isAssignableFrom(EventDetailViewModel.class)) {
            return (T) new EventDetailViewModel(eventSaveInsert, eventSaveUpdate);

        }
        throw new IllegalArgumentException("not support the class:" + modelClass);
    }
}
