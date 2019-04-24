package com.chris.eban.presenter.event

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.chris.eban.domain.Result
import com.chris.eban.domain.entity.DMEventListItem
import com.chris.eban.domain.usecase.EventListQuery
import com.chris.eban.domain.usecase.EventSaveDelete
import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable
import timber.log.Timber

class EventListViewModel(private val listQuery: EventListQuery,
                         private val saveDelete: EventSaveDelete) : ViewModel(),
        SingleObserver<Result<List<DMEventListItem>>> {

    val hasData = MutableLiveData<Boolean>()
    val eventList = MutableLiveData<MutableList<EventItem>>()
    private val mapper: EventListMapper = EventListMapper()
    private var disposable: Disposable? = null

    fun init() {
        Timber.tag(TAG).d("init: this is viewModel and has data :%s", hasData.value)
        listQuery.execute().subscribe(this)
    }

    override fun onSubscribe(d: Disposable) {
        checkDisposable()
        disposable = d
    }

    override fun onSuccess(listResult: Result<List<DMEventListItem>>) {
        Timber.tag(TAG).d("onSuccess: listSize:%s", listResult.content.size)
        val eventItems = mapper.map(listResult.content)
        if (eventItems.isNotEmpty())
            hasData.setValue(true)
        else
            hasData.setValue(false)
        eventList.value = eventItems

        Timber.tag(TAG).d("onSuccess: has data %s", hasData.value)
    }

    override fun onError(e: Throwable) {
        Timber.tag(TAG).e(e, "onError: ")
        hasData.value = false
        Timber.tag(TAG).d("onError: has data %s", hasData.value)
    }

    override fun onCleared() {
        super.onCleared()
        checkDisposable()
    }

    private fun checkDisposable() {
        disposable?.dispose()
    }

    fun queryChanged() {
        // TODO: 4/23/2019 query changed item
        init()
    }

    fun removeItem(item: EventItem) {
//        Objects.requireNonNull<List<EventItem>>(eventList.value).remove(item)
        eventList.value?.remove(item)
        saveDelete.setItem(item)
        saveDelete.execute().subscribe()
    }

    companion object {

        private const val TAG = "EventListViewModel"
    }
}
