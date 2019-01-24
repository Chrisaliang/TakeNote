package com.chris.eban;


import com.chris.eban.common.EBanViewModelProviderFactory;

import javax.inject.Singleton;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import dagger.Module;
import dagger.Provides;

@Module
public class ViewModelModule {
    @Singleton
    @Provides
    static ViewModelProvider.Factory providerFactory() {
        return new EBanViewModelProviderFactory();
    }
}
