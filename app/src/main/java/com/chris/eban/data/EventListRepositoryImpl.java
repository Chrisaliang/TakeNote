package com.chris.eban.data;

import com.chris.eban.domain.EventListRepository;
import com.chris.eban.domain.entity.DMEventListItem;

import java.util.ArrayList;
import java.util.List;

public class EventListRepositoryImpl implements EventListRepository {
    @Override
    public List<DMEventListItem> queryEventList() {
        ArrayList<DMEventListItem> dmEventListItems = new ArrayList<>();
        // TODO: 2019/3/6  查询数据库中事件
        for (int i = 0; i < 20; i++) {
            dmEventListItems.add(new DMEventListItem());
        }
        return dmEventListItems;
    }
}
