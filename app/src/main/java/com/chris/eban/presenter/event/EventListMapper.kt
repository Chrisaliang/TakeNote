package com.chris.eban.presenter.event


import android.icu.text.SimpleDateFormat

import com.chris.eban.domain.entity.DMEventListItem
import timber.log.Timber
import java.util.*

class EventListMapper {


    companion object {
        private const val pattern: String = "MMM dd, yyyy"
        private const val patternToday: String = "HH:mm"

        private const val TAG = "EventListMapper"
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
        item.createDate = dmEventListItem.createDate
        return item
    }

    private fun getTimeStr(date: Date): String? {
        Timber.tag(TAG).d("Time is %d", date.time)
        val sdf = SimpleDateFormat(pattern, Locale.getDefault())

        val calendar = Calendar.getInstance()
        calendar.time = Date()
        calendar[Calendar.HOUR_OF_DAY] = 0
        calendar[Calendar.MINUTE] = 0
        calendar[Calendar.SECOND] = 0
        val today = calendar.time
        val days = ((today.time - date.time) / (1000 * 60 * 60 * 24L))

        if (days.compareTo(0) == 0)
            sdf.applyPattern(patternToday)
        else
            sdf.applyPattern(pattern)
        return sdf.format(date)

    }

    fun map(item: EventItem): DMEventListItem {
        val dmEventListItem = DMEventListItem()
        dmEventListItem.id = item.id

        dmEventListItem.content = item.content!!
        dmEventListItem.title = item.title!!
        if (item.id == 0L)
            dmEventListItem.createDate = Date()
        else
            dmEventListItem.createDate = item.createDate
        dmEventListItem.updateDate = Date()
        return dmEventListItem
    }
}
