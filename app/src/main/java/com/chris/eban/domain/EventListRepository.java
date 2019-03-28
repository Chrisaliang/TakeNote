package com.chris.eban.domain;

import com.chris.eban.domain.entity.DMEventListItem;

import java.util.List;

public interface EventListRepository {
    /**
     * 事件列表信息
     *
     * @return 列表条目
     */
    List<DMEventListItem> queryEventList();

    /**
     * 存储一条事件条目
     * @param item 事件
     */
    boolean saveEvent(DMEventListItem item);

    /**
     * 更新一条记录
     * @param item 记录
     */
    boolean updateEvent(DMEventListItem item);

}
