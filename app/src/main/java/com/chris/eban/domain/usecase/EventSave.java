package com.chris.eban.domain.usecase;

import com.chris.eban.domain.EventListRepository;
import com.chris.eban.domain.JobThread;
import com.chris.eban.domain.SingleUseCase;
import com.chris.eban.presenter.event.EventItem;
import com.chris.eban.presenter.event.EventListMapper;

import io.reactivex.Single;
import io.reactivex.functions.Function;
import timber.log.Timber;

public class EventSave extends SingleUseCase<Boolean> {

    private static final String TAG = "EventSave";

    private final EventListMapper mapper;
    private JobThread jobThread;
    private EventListRepository repository;

    public void setItem(EventItem item) {
        this.item = item;
    }

    // TODO: 2019/3/11 改用domain层数据对象
    private EventItem item;

    public EventSave(JobThread jobThread, EventListRepository repository) {
        this.jobThread = jobThread;
        this.repository = repository;
        mapper = new EventListMapper();
    }

    @Override
    protected Single<Boolean> buildSingle() {
        return Single.just(repository)
                .map(new Function<EventListRepository, Boolean>() {
                    @Override
                    public Boolean apply(EventListRepository repository) {
                        Timber.tag(TAG).d("save a event:%s", item);
                        return repository.saveEvent(mapper.map(item));
                    }
                })
                .subscribeOn(jobThread.provideWorker())
                .observeOn(jobThread.provideUI());
    }
}
