package com.chris.eban.presenter;

import com.chris.eban.domain.entity.DMEventListItem;

import java.util.ArrayList;
import java.util.List;

class EventListMapper {
    List<EventItem> map(List<DMEventListItem> content) {
        ArrayList<EventItem> eventItems = new ArrayList<>();
        for (int i = 0; i < content.size(); i++) {
            eventItems.add(map(content.get(i)));
        }
        return eventItems;
    }

    private EventItem map(DMEventListItem dmEventListItem) {
        EventItem eventItem = new EventItem();
        // TODO: 2019/1/28 转换
        return eventItem;
    }
}
