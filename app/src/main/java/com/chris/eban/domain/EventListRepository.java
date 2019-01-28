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

}
