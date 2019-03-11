package com.chris.eban.domain.usecase;

import com.chris.eban.domain.EventListRepository;
import com.chris.eban.domain.JobThread;
import com.chris.eban.domain.SingleUseCase;
import com.chris.eban.domain.entity.DMEventListItem;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.functions.Function;

public class EventListQuery extends SingleUseCase<List<DMEventListItem>> {

    private JobThread jobThread;
    private EventListRepository repository;

    public EventListQuery(JobThread jobThread, EventListRepository repository) {
        this.jobThread = jobThread;
        this.repository = repository;
    }

    @Override
    protected Single<List<DMEventListItem>> buildSingle() {
        return Single.just(repository)
                .map(new Function<EventListRepository, List<DMEventListItem>>() {
                    @Override
                    public List<DMEventListItem> apply(EventListRepository repository) {
                        return repository.queryEventList();
                    }
                })
                .subscribeOn(jobThread.provideWorker())
                .observeOn(jobThread.provideUI());
    }
}
