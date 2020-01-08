package com.chris.eban.data

import com.chris.eban.domain.entity.DMEventListItem
import java.util.*

internal class EventMapper {

    fun map(events: List<DataEvent>): List<DMEventListItem> {

        val dmEventListItems = ArrayList<DMEventListItem>()
        for (event in events) {
            dmEventListItems.add(map(event))
        }
        return dmEventListItems
    }

    fun map(event: DataEvent): DMEventListItem {

        val dmEventListItem = DMEventListItem()
        dmEventListItem.id = event.id
        dmEventListItem.title = event.title
        dmEventListItem.content = event.content

        dmEventListItem.createDate = event.createTime
        dmEventListItem.updateDate = event.updateTime
        return dmEventListItem
    }

    fun map(item: DMEventListItem): DataEvent {
        return DataEvent(item.id, item.title, item.content, item.createDate, item.updateDate)
    }
}
