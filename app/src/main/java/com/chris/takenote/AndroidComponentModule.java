package com.chris.takenote;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@SuppressWarnings("unused")
@Module
abstract class AndroidComponentModule {

    @ContributesAndroidInjector
    public abstract MainActivity mainActivity();
}
