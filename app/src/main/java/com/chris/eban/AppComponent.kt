package com.chris.eban

import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
        modules = [
            AndroidSupportInjectionModule::class,
            AndroidComponentModule::class,
            NetModule::class,
            ViewModelModule::class,
            UseCaseModule::class,
            SchedulerModule::class,
            PersistentModule::class,
            RepositoryModule::class,
            JsonModule::class
        ]
)
interface AppComponent : AndroidInjector<App> {

    @Component.Factory
    abstract class Builder : AndroidInjector.Factory<App>
}
