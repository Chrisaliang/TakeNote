package com.chris.eban.data;

import com.chris.eban.domain.EventListRepository;
import com.chris.eban.domain.entity.DMEventListItem;

import java.util.List;

import timber.log.Timber;

public class EventListRepositoryImpl implements EventListRepository {

    private static final String TAG = "EventListRepositoryImpl";

    private final EventDao eventDao;
    private final EventMapper mapper;

    public EventListRepositoryImpl(EventDao eventDao) {
        this.eventDao = eventDao;
        mapper = new EventMapper();
    }

    @Override
    public List<DMEventListItem> queryEventList() {
        List<Event> events = eventDao.queryEventList();
        Timber.tag(TAG).d("events size:%d", events.size());
        return mapper.map(events);
    }

    @Override
    public long saveEvent(DMEventListItem item) {
        Event event = mapper.map(item);
        Timber.tag(TAG).d("insert event: %s", event);
        long insertEvent = eventDao.insertEvent(event);
        Timber.tag(TAG).d("insert id: %d", insertEvent);
        return insertEvent;
    }

    @Override
    public long updateEvent(DMEventListItem item) {
        Event event = mapper.map(item);
        eventDao.updateEvent(event);
        return item.id;
    }
}
