package com.chris.eban.domain

import com.chris.eban.domain.entity.DMEventListItem

interface EventListRepository {
    /**
     * 事件列表信息
     *
     * @return 列表条目
     */
    fun queryEventList(): List<DMEventListItem>

    /**
     * 存储一条事件条目
     * @param item 事件
     */
    fun saveEvent(item: DMEventListItem): Long

    /**
     * 更新一条记录
     *
     * @param item 记录
     */
    fun updateEvent(item: DMEventListItem): Long

    /**
     * 删除一条记录
     */
    fun deleteEvent(item: DMEventListItem): Long

}
