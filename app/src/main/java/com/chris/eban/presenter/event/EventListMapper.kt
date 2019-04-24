package com.chris.eban.presenter.event

import com.chris.eban.domain.entity.DMEventListItem
import java.util.*

class EventListMapper {

    internal fun map(content: List<DMEventListItem>): MutableList<EventItem> {
        val eventItems = ArrayList<EventItem>()
        for (i in content.indices) {
            eventItems.add(map(content[i]))
        }
        return eventItems
    }

    fun map(dmEventListItem: DMEventListItem): EventItem {
        return EventItem(dmEventListItem.id, dmEventListItem.title, dmEventListItem.content)
    }

    fun map(item: EventItem): DMEventListItem {
        val dmEventListItem = DMEventListItem()
        dmEventListItem.id = item.id
        dmEventListItem.content = item.content
        dmEventListItem.title = item.title
        return dmEventListItem
    }
}
