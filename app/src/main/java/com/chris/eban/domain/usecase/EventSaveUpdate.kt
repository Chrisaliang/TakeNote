package com.chris.eban.domain.usecase

import com.chris.eban.domain.EventListRepository
import com.chris.eban.domain.JobThread
import com.chris.eban.domain.SingleUseCase
import com.chris.eban.presenter.event.EventItem
import com.chris.eban.presenter.event.EventListMapper

import io.reactivex.Single

class EventSaveUpdate(private val jobThread: JobThread,
                      private val repository: EventListRepository) : SingleUseCase<Long>() {

    private val mapper: EventListMapper = EventListMapper()

    // TODO: 2019/3/28 改用domain层数据对象
    private var item: EventItem? = null

    fun setItem(item: EventItem) {
        this.item = item
    }

    override fun buildSingle(): Single<Long>? {
        return Single.just(repository)
                .map { repository -> repository.updateEvent(mapper.map(item!!)) }
                .subscribeOn(jobThread.provideWorker())
                .observeOn(jobThread.provideUI())
    }
}
