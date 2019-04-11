package com.chris.eban.presenter.event;

import com.chris.eban.domain.entity.DMEventListItem;

import java.util.ArrayList;
import java.util.List;

public class EventListMapper {
    List<EventItem> map(List<DMEventListItem> content) {
        ArrayList<EventItem> eventItems = new ArrayList<>();
        for (int i = 0; i < content.size(); i++) {
            eventItems.add(map(content.get(i)));
        }
        return eventItems;
    }

    public EventItem map(DMEventListItem dmEventListItem) {
        return new EventItem(dmEventListItem.id, dmEventListItem.title, dmEventListItem.content);
    }

    public DMEventListItem map(EventItem item) {
        DMEventListItem dmEventListItem = new DMEventListItem();
        dmEventListItem.id = item.id;
        dmEventListItem.content = item.content;
        dmEventListItem.title = item.title;
        return dmEventListItem;
    }
}
