package com.chris.eban.presenter;

import android.util.Log;

import com.chris.eban.domain.Result;
import com.chris.eban.domain.entity.DMEventListItem;
import com.chris.eban.domain.usecase.EventListQuery;

import java.util.List;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

public class EventListViewModel extends ViewModel implements SingleObserver<Result<List<DMEventListItem>>> {

    private static final String TAG = "EventListViewModel";
    public final MutableLiveData<Boolean> hasData = new MutableLiveData<>();
    private final MutableLiveData<List<EventItem>> eventList = new MutableLiveData<>();
    private EventListQuery listQuery;
    private EventListMapper mapper;
    private Disposable disposable;

    public EventListViewModel(EventListQuery listQuery) {
        this.listQuery = listQuery;
        mapper = new EventListMapper();
    }

    void init() {
        Log.d(TAG, "init: this is viewModel and has data :" + hasData.getValue());
        listQuery.execute().subscribe(this);
    }

    @Override
    public void onSubscribe(Disposable d) {
        checkDisposable();
        disposable = d;
    }

    @Override
    public void onSuccess(Result<List<DMEventListItem>> listResult) {
        Log.d(TAG, "onSuccess: listSize:" + listResult.content.size());
        List<EventItem> eventItems = mapper.map(listResult.content);
        if (eventItems != null && eventItems.size() > 0)
            hasData.setValue(true);
        else hasData.setValue(false);
        eventList.setValue(eventItems);

        Log.d(TAG, "onSuccess: has data " + hasData.getValue());
    }

    @Override
    public void onError(Throwable e) {
        Log.e(TAG, "onError: ", e);
        hasData.setValue(false);
        Log.d(TAG, "onError: has data " + hasData.getValue());
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
}
