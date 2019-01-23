package com.chris.eban;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@SuppressWarnings("unused")
@Module
abstract class AndroidComponentModule {

    @ContributesAndroidInjector
    public abstract MainActivity mainActivity();
}
