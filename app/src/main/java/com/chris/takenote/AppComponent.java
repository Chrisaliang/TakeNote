package com.chris.takenote;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {
        AndroidSupportInjectionModule.class,
        AndroidComponentModule.class,
        JsonModule.class
})
public interface AppComponent extends AndroidInjector<App> {

    @dagger.Component.Builder
    abstract class Builder extends AndroidInjector.Builder<App> {
    }
}