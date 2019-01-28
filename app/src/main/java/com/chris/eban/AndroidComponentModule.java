package com.chris.eban;

import com.chris.eban.presenter.MainActivity;
import com.chris.eban.presenter.MainFragment;
import com.chris.eban.presenter.event.EventListFragment;

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

    @ContributesAndroidInjector
    public abstract EventListFragment eventListFragment();
}
