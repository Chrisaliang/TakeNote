package com.chris.eban;

import com.chris.eban.domain.JobThread;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@Module
abstract class SchedulerModule {

    @Provides
    @Singleton
    static JobThread provideJobThread() {
        return new JobThread() {
            @Override
            public Scheduler provideUI() {
                return AndroidSchedulers.mainThread();
            }

            @Override
            public Scheduler provideWorker() {
                return Schedulers.io();
            }
        };
    }
}
