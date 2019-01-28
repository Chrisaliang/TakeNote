package com.chris.eban.data;

import com.chris.eban.domain.EventListRepository;
import com.chris.eban.domain.entity.DMEventListItem;

import java.util.ArrayList;
import java.util.List;

public class EventListRepositoryImpl implements EventListRepository {
    @Override
    public List<DMEventListItem> queryEventList() {
        ArrayList<DMEventListItem> dmEventListItems = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            dmEventListItems.add(new DMEventListItem());
        }
        return dmEventListItems;
    }
}
