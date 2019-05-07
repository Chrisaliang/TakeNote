package com.chris.eban.presenter.event

import android.icu.text.SimpleDateFormat
import com.chris.eban.domain.entity.DMEventListItem
import java.util.*

class EventListMapper {

    companion object {
        private const val pattern: String = "yyyyMMMdd"
    }

    internal fun map(content: List<DMEventListItem>): MutableList<EventItem> {
        val eventItems = ArrayList<EventItem>()
        for (i in content.indices) {
            eventItems.add(map(content[i]))
        }
        return eventItems
    }

    fun map(dmEventListItem: DMEventListItem): EventItem {
        val item = EventItem()
        item.id = dmEventListItem.id
        item.title = dmEventListItem.title
        item.content = dmEventListItem.content
        item.createTime = getTimeStr(dmEventListItem.createDate)
        item.updateTime = getTimeStr(dmEventListItem.updateDate)
        return item
    }

    private fun getTimeStr(date: Date): String? {
        val sdf = SimpleDateFormat(pattern, Locale.getDefault())
        return sdf.format(date)
    }

    fun map(item: EventItem): DMEventListItem {
        val dmEventListItem = DMEventListItem()
        dmEventListItem.id = item.id
//        dmEventListItem.content = item.content
//        dmEventListItem.title = item.title
//        dmEventListItem.createDate = item.createTime
//        dmEventListItem.updateDate = item.updateTime
        return dmEventListItem
    }
}
