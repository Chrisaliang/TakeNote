package com.chris.eban.domain.usecase;

import com.chris.eban.domain.EventListRepository;
import com.chris.eban.domain.JobThread;
import com.chris.eban.domain.SingleUseCase;
import com.chris.eban.presenter.event.EventItem;
import com.chris.eban.presenter.event.EventListMapper;

import io.reactivex.Single;
import io.reactivex.functions.Function;

public class EventSaveUpdate extends SingleUseCase<Long> {


    private final EventListMapper mapper;
    private JobThread jobThread;
    private EventListRepository repository;

    // TODO: 2019/3/28 改用domain层数据对象
    private EventItem item;

    public void setItem(EventItem item) {
        this.item = item;
    }

    public EventSaveUpdate(JobThread jobThread, EventListRepository repository) {
        this.jobThread = jobThread;
        this.repository = repository;
        mapper = new EventListMapper();
    }

    @Override
    protected Single<Long> buildSingle() {
        return Single.just(repository)
                .map(new Function<EventListRepository, Long>() {
                    @Override
                    public Long apply(EventListRepository repository) {
                        return repository.updateEvent(mapper.map(item));
                    }
                })
                .subscribeOn(jobThread.provideWorker())
                .observeOn(jobThread.provideUI());
    }
}
