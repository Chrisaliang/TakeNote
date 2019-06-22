package com.chris.eban.domain.usecase

import com.chris.eban.domain.EventListRepository
import com.chris.eban.domain.JobThread
import com.chris.eban.domain.SingleUseCase
import com.chris.eban.domain.entity.DMEventListItem
import io.reactivex.Single

class EventItemQuery(private val jobThread: JobThread,
                     private val repository: EventListRepository)
    : SingleUseCase<DMEventListItem>() {

    private var itemId: Long = -1

    fun setItemId(itemId: Long) {
        this.itemId = itemId
    }

    override fun buildSingle(): Single<DMEventListItem>? {

        return Single.just(repository)
                .map { repository -> repository.queryEventItem(itemId) }
                .subscribeOn(jobThread.provideWorker())
                .observeOn(jobThread.provideUI())
    }
}