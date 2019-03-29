package com.chris.eban.presenter.event;

import com.chris.eban.domain.Result;
import com.chris.eban.domain.usecase.EventSaveInsert;
import com.chris.eban.domain.usecase.EventSaveUpdate;

import java.util.Objects;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import timber.log.Timber;

public class EventDetailViewModel extends ViewModel implements SingleObserver<Result<Long>> {

    private static final String TAG = "EventDetailViewModel";

    public final MutableLiveData<EventItem> item = new MutableLiveData<>();
    private EventItem newItem;
    private EventSaveInsert eventSaveInsert;
    private EventSaveUpdate eventSaveUpdate;
    private Disposable disposable;

    public EventDetailViewModel(EventSaveInsert eventSaveInsert,
                                EventSaveUpdate eventSaveUpdate) {
        this.eventSaveInsert = eventSaveInsert;
        this.eventSaveUpdate = eventSaveUpdate;
    }

    public void setItem(EventItem item) {
        this.item.setValue(item);
    }

    void setItem(CharSequence title, CharSequence content) {
        // TODO: 2019/3/29 compare
        newItem = new EventItem(title.toString(), content.toString());

        EventItem value = item.getValue();
        if (value != null) {
            value.title = title.toString();
            value.content = content.toString();
        } else {
            value = new EventItem(title.toString(), content.toString());
        }
        item.setValue(value);
    }

    void saveEvent() {

        EventItem value = item.getValue();
        if (value == null) return;
        if (value.id == 0) {
            // insert to db
            eventSaveInsert.setItem(value);
            eventSaveInsert.execute().subscribe(this);
        } else {
            // update db
            eventSaveUpdate.setItem(value);
            eventSaveUpdate.execute().subscribe(this);
        }


    }

    @Override
    public void onSubscribe(Disposable d) {
        checkDisposable();
        disposable = d;
    }

    @Override
    public void onSuccess(Result<Long> longResult) {
        Objects.requireNonNull(item.getValue()).id = longResult.content;
    }

    @Override
    public void onError(Throwable e) {
        Timber.tag(TAG).e(e, "onError: ");
        checkDisposable();
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


}
