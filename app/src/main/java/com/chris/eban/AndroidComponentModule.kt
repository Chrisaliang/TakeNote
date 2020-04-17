package com.chris.eban

import com.chris.eban.presenter.MainActivity
import com.chris.eban.presenter.ManagerFragment
import com.chris.eban.presenter.event.EventDetailActivity
import com.chris.eban.presenter.event.EventListFragment

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
internal abstract class AndroidComponentModule {

    @ContributesAndroidInjector
    abstract fun mainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun createEventActivity(): EventDetailActivity


    //----------------  fragment   -----------------

    //    @ContributesAndroidInjector
    //    public abstract MainFragment mainFragment();

    @ContributesAndroidInjector
    abstract fun eventListFragment(): EventListFragment

    @ContributesAndroidInjector
    abstract fun managerFragment(): ManagerFragment
}
