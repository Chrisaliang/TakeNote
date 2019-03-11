package com.chris.eban.data;

import com.chris.eban.domain.entity.DMEventListItem;

import java.util.ArrayList;
import java.util.List;

class EventMapper {
    List<DMEventListItem> map(List<Event> events) {
        ArrayList<DMEventListItem> dmEventListItems = new ArrayList<>();
        for (Event event : events) {
            dmEventListItems.add(map(event));
        }
        return dmEventListItems;
    }

    private DMEventListItem map(Event event) {
        DMEventListItem dmEventListItem = new DMEventListItem();
        dmEventListItem.title = event.title;
        dmEventListItem.content = event.content;
        return dmEventListItem;
    }
}
