package com.chris.eban.presenter.event;

import com.chris.eban.domain.Result;
import com.chris.eban.domain.entity.DMEventListItem;
import com.chris.eban.domain.usecase.EventListQuery;
import com.chris.eban.domain.usecase.EventSaveDelete;

import java.util.List;
import java.util.Objects;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import timber.log.Timber;

public class EventListViewModel extends ViewModel implements SingleObserver<Result<List<DMEventListItem>>> {

    private static final String TAG = "EventListViewModel";

    public final MutableLiveData<Boolean> hasData = new MutableLiveData<>();
    private final MutableLiveData<List<EventItem>> eventList = new MutableLiveData<>();
    private EventListQuery listQuery;
    private EventSaveDelete saveDelete;
    private EventListMapper mapper;
    private Disposable disposable;

    public EventListViewModel(EventListQuery listQuery, EventSaveDelete saveDelete) {
        this.listQuery = listQuery;
        this.saveDelete = saveDelete;
        mapper = new EventListMapper();
    }

    void init() {
        Timber.tag(TAG).d("init: this is viewModel and has data :%s", hasData.getValue());
        listQuery.execute().subscribe(this);
    }

    @Override
    public void onSubscribe(Disposable d) {
        checkDisposable();
        disposable = d;
    }

    @Override
    public void onSuccess(Result<List<DMEventListItem>> listResult) {
        Timber.tag(TAG).d("onSuccess: listSize:%s", listResult.content.size());
        List<EventItem> eventItems = mapper.map(listResult.content);
        if (eventItems != null && eventItems.size() > 0)
            hasData.setValue(true);
        else hasData.setValue(false);
        eventList.setValue(eventItems);

        Timber.tag(TAG).d("onSuccess: has data %s", hasData.getValue());
    }

    @Override
    public void onError(Throwable e) {
        Timber.tag(TAG).e(e, "onError: ");
        hasData.setValue(false);
        Timber.tag(TAG).d("onError: has data %s", hasData.getValue());
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        checkDisposable();
    }

    private void checkDisposable() {
        if (disposable != null)
            disposable.dispose();
    }

    MutableLiveData<List<EventItem>> getEventList() {
        return eventList;
    }

    void query() {
        init();
    }

    void removeItem(EventItem item) {
        Objects.requireNonNull(eventList.getValue()).remove(item);
        saveDelete.setItem(item);
        saveDelete.execute().subscribe();
    }
}
