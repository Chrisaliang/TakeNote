package com.chris.eban.common;

import com.chris.eban.domain.usecase.EventListQuery;
import com.chris.eban.presenter.EventListViewModel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class EBanViewModelProviderFactory implements ViewModelProvider.Factory {

    private EventListQuery eventListQuery;

    public EBanViewModelProviderFactory(EventListQuery eventListQuery) {
        this.eventListQuery = eventListQuery;
    }

    @SuppressWarnings("unchecked")
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(EventListViewModel.class)) {
            return (T) new EventListViewModel(eventListQuery);
        }
        throw new IllegalArgumentException("not support the class:" + modelClass);
    }
}
