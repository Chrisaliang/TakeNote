package com.chris.eban.domain.usecase

import com.chris.eban.domain.EventListRepository
import com.chris.eban.domain.JobThread
import com.chris.eban.domain.SingleUseCase
import com.chris.eban.domain.entity.DMEventListItem
import io.reactivex.Single
import timber.log.Timber

class EventSaveInsert(private val jobThread: JobThread,
                      private val repository: EventListRepository) : SingleUseCase<Long>() {

    private lateinit var item: DMEventListItem

    fun setItem(item: DMEventListItem) {
        this.item = item
    }

    override fun buildSingle(): Single<Long>? {
        return Single.just(repository)
                .map { repository ->
                    Timber.tag(TAG).d("save a event:%s", item)
                    repository.saveEvent(item)
                }
                .subscribeOn(jobThread.provideWorker())
                .observeOn(jobThread.provideUI())
    }

    companion object {
        private const val TAG = "EventSaveInsert"
    }
}
