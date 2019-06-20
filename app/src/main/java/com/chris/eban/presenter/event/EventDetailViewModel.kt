package com.chris.eban.presenter.event

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.chris.eban.domain.Result
import com.chris.eban.domain.entity.DMEventListItem
import com.chris.eban.domain.usecase.EventItemQuery
import com.chris.eban.domain.usecase.EventSaveDelete
import com.chris.eban.domain.usecase.EventSaveInsert
import com.chris.eban.domain.usecase.EventSaveUpdate
import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable
import timber.log.Timber
import java.util.*

class EventDetailViewModel(private val eventItemQuery: EventItemQuery,
                           private val eventSaveInsert: EventSaveInsert,
                           private val eventSaveUpdate: EventSaveUpdate,
                           private val eventSaveDelete: EventSaveDelete)
    : ViewModel(), SingleObserver<Result<Long>> {

    val item = MutableLiveData<EventItem>()
    private var mapper = EventListMapper()
    private var saved = true
    private var disposable: Disposable? = null

    fun setItem(item: EventItem) {
        this.item.value = item
    }

    fun queryItemById(itemId: Long) {
        eventItemQuery.setItemId(itemId)
        // TODO get the result
        eventItemQuery.execute().subscribe { t: Result<DMEventListItem>? -> setItem(mapper.map(t!!.content)) }
    }

    fun setItem(title: CharSequence, content: CharSequence) {

        val newItem = EventItem(title.toString(), content.toString())

        var value = item.value
        if (value == null) {
            value = newItem
            saved = false
        } else {
            if (value.title != newItem.title) {
                value.title = newItem.title
                saved = false
            }
            if (value.content != newItem.content) {
                value.content = newItem.content
                saved = false
            }
        }
        item.value = value
    }

    fun saveEvent() {
        if (saved) return
        saved = true

        val value = item.value ?: return
        if (value.id == 0L) {
            // insert to db
            eventSaveInsert.setItem(mapper.map(value))
            eventSaveInsert.execute().subscribe(this)
        } else {
            // update db
            eventSaveUpdate.setItem(mapper.map(value))
            eventSaveUpdate.execute().subscribe(this)
        }
    }

    fun deleteEvent() {
        // delete from db
        val value = item.value ?: return
        eventSaveDelete.setItem(value)
        eventSaveDelete.execute().subscribe(this)
    }

    override fun onSubscribe(d: Disposable) {
        checkDisposable()
        disposable = d
    }

    override fun onSuccess(longResult: Result<Long>) {
        // todo deal the result from room
        Objects.requireNonNull<EventItem>(item.value).id = longResult.content
    }

    override fun onError(e: Throwable) {
        Timber.tag(TAG).e(e, "onError: ")
        checkDisposable()
    }

    override fun onCleared() {
        super.onCleared()
        checkDisposable()
    }

    private fun checkDisposable() {
        if (disposable != null)
            disposable!!.dispose()
    }

    fun hadId(): Boolean {
        return item.value != null && item.value!!.id > 0
    }

    companion object {
        private const val TAG = "EventDetailViewModel"
    }
}
