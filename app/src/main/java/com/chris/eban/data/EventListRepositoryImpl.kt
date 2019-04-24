package com.chris.eban.data

import com.chris.eban.domain.EventListRepository
import com.chris.eban.domain.entity.DMEventListItem

import timber.log.Timber

class EventListRepositoryImpl(private val eventDao: EventDao) : EventListRepository {
    private val mapper: EventMapper = EventMapper()

    override fun queryEventList(): List<DMEventListItem> {
        val events = eventDao.queryEventList()
        Timber.tag(TAG).d("events size:%d", events.size)
        return mapper.map(events)
    }

    override fun saveEvent(item: DMEventListItem): Long {
        val event = mapper.map(item)
        Timber.tag(TAG).d("insert event: %s", event)
        val insertEvent = eventDao.insertEvent(event)
        Timber.tag(TAG).d("insert id: %d", insertEvent)
        return insertEvent
    }

    override fun updateEvent(item: DMEventListItem): Long {
        val event = mapper.map(item)
        eventDao.updateEvent(event)
        Timber.tag(TAG).w("update item:%s", event)
        return item.id
    }

    override fun deleteEvent(item: DMEventListItem): Long {
        val event = mapper.map(item)
        eventDao.deleteEvent(event)
        Timber.tag(TAG).w("delete an event:%s", event)
        return event.id
    }

    companion object {
        private const val TAG = "EventListRepositoryImpl"
    }
}
