package com.chris.eban.common;

import com.chris.eban.presenter.MainViewModel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class EBanViewModelProviderFactory implements ViewModelProvider.Factory {

    @SuppressWarnings("unchecked")
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MainViewModel.class))
            return (T) new MainViewModel();
        throw new IllegalArgumentException("not support the class:" + modelClass);
    }
}
