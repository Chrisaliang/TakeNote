package com.chris.eban;

import com.chris.eban.presenter.MainActivity;
import com.chris.eban.presenter.MainFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@SuppressWarnings("unused")
@Module
abstract class AndroidComponentModule {

    @ContributesAndroidInjector
    public abstract MainActivity mainActivity();


    //----------------  fragment   -----------------

    @ContributesAndroidInjector
    public abstract MainFragment mainFragment();
}
