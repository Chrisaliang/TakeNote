package com.chris.eban.domain.usecase

import com.chris.eban.domain.EventListRepository
import com.chris.eban.domain.JobThread
import com.chris.eban.domain.SingleUseCase

import com.chris.eban.domain.entity.DMEventListItem

import io.reactivex.Single

class EventSaveUpdate(private val jobThread: JobThread,
                      private val repository: EventListRepository) : SingleUseCase<Long>() {


    private var item: DMEventListItem? = null

    fun setItem(item: DMEventListItem) {

        this.item = item
    }

    override fun buildSingle(): Single<Long>? {
        return Single.just(repository)

                .map { repository -> repository.updateEvent(item!!) }

                .subscribeOn(jobThread.provideWorker())
                .observeOn(jobThread.provideUI())
    }
}
