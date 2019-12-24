package com.chris.eban.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.chris.eban.domain.usecase.*
import com.chris.eban.presenter.event.EventDetailViewModel
import com.chris.eban.presenter.event.EventListViewModel

class EBanViewModelProviderFactory(
        private val eventListQuery: EventListQuery,

        private val eventItemQuery: EventItemQuery,

        private val eventSaveInsert: EventSaveInsert,
        private val eventSaveUpdate: EventSaveUpdate,
        private val eventSaveDelete: EventSaveDelete
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EventListViewModel::class.java)) {
            return EventListViewModel(eventListQuery, eventSaveDelete) as T
        } else if (modelClass.isAssignableFrom(EventDetailViewModel::class.java)) {
            return EventDetailViewModel(eventItemQuery, eventSaveInsert, eventSaveUpdate, eventSaveDelete) as T
        }
        throw IllegalArgumentException("not support the class:$modelClass")
    }
}
