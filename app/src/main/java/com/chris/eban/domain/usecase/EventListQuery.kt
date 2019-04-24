package com.chris.eban.domain.usecase

import com.chris.eban.domain.EventListRepository
import com.chris.eban.domain.JobThread
import com.chris.eban.domain.SingleUseCase
import com.chris.eban.domain.entity.DMEventListItem
import io.reactivex.Single

class EventListQuery(private val jobThread: JobThread,
                     private val repository: EventListRepository)
    : SingleUseCase<List<DMEventListItem>>() {

    override fun buildSingle(): Single<List<DMEventListItem>>? {
        return Single.just(repository)
                .map { repository -> repository.queryEventList() }
                .subscribeOn(jobThread.provideWorker())
                .observeOn(jobThread.provideUI())
    }
}
