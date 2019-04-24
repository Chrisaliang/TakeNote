package com.chris.eban.data

import com.chris.eban.domain.entity.DMEventListItem
import java.util.*

internal class EventMapper {
    fun map(events: List<Event>): List<DMEventListItem> {
        val dmEventListItems = ArrayList<DMEventListItem>()
        for (event in events) {
            dmEventListItems.add(map(event))
        }
        return dmEventListItems
    }

    fun map(event: Event): DMEventListItem {
        val dmEventListItem = DMEventListItem()
        dmEventListItem.id = event.id
        dmEventListItem.title = event.title
        dmEventListItem.content = event.content
        return dmEventListItem
    }

    fun map(item: DMEventListItem): Event {
        val event = Event()
        event.id = item.id
        event.content = item.content
        event.title = item.title
        return event
    }
}
