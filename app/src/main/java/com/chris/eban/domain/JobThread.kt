package com.chris.eban.domain

import io.reactivex.Scheduler

interface JobThread {
    /**
     * ui thread
     */
    fun provideUI(): Scheduler

    /**
     * worker thread
     */
    fun provideWorker(): Scheduler
}
