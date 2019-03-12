package com.chris.eban;

import com.chris.eban.presenter.EventDetailActivity;
import com.chris.eban.presenter.MainActivity;
import com.chris.eban.presenter.event.EventListFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@SuppressWarnings("unused")
@Module
abstract class AndroidComponentModule {

    @ContributesAndroidInjector
    public abstract MainActivity mainActivity();

    @ContributesAndroidInjector
    public abstract EventDetailActivity createEventActivity();


    //----------------  fragment   -----------------

//    @ContributesAndroidInjector
//    public abstract MainFragment mainFragment();

    @ContributesAndroidInjector
    public abstract EventListFragment eventListFragment();
}
