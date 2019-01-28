package com.chris.eban.domain;

import io.reactivex.Scheduler;

public interface JobThread {
    /**
     * ui thread
     */
    Scheduler provideUI();

    /**
     * worker thread
     */
    Scheduler provideWorker();
}
