package com.chris.eban

import com.chris.eban.domain.JobThread
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Singleton

@Module
internal object SchedulerModule {

    @Provides
    @Singleton
    fun provideJobThread(): JobThread {
        return object : JobThread {
            override fun provideUI(): Scheduler {
                return AndroidSchedulers.mainThread()
            }

            override fun provideWorker(): Scheduler {
                return Schedulers.io()
            }
        }
    }
}
