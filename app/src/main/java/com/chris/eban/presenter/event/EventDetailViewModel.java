package com.chris.eban.presenter.event;

import com.chris.eban.domain.Result;
import com.chris.eban.domain.usecase.EventSaveDelete;
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
    private boolean saved = true;
    private EventSaveInsert eventSaveInsert;
    private EventSaveUpdate eventSaveUpdate;
    private EventSaveDelete eventSaveDelete;
    private Disposable disposable;

    public EventDetailViewModel(EventSaveInsert eventSaveInsert,
                                EventSaveUpdate eventSaveUpdate,
                                EventSaveDelete eventSaveDelete) {
        this.eventSaveInsert = eventSaveInsert;
        this.eventSaveUpdate = eventSaveUpdate;
        this.eventSaveDelete = eventSaveDelete;
    }

    public void setItem(EventItem item) {
        this.item.setValue(item);
    }

    void setItem(CharSequence title, CharSequence content) {

        EventItem newItem = new EventItem(title.toString(), content.toString());

        EventItem value = item.getValue();
        if (value == null) {
            value = newItem;
            saved = false;
        } else {
            if (!value.title.equals(newItem.title)) {
                value.title = newItem.title;
                saved = false;
            }
            if (!value.content.equals(newItem.content)) {
                value.content = newItem.content;
                saved = false;
            }
        }
        item.setValue(value);
    }

    void saveEvent() {
        if (saved) return;
        saved = true;

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

    void deleteEvent() {
        // delete from db
        EventItem value = item.getValue();
        if (value == null) return;
        eventSaveDelete.setItem(value);
        eventSaveDelete.execute().subscribe(this);
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


    boolean hadId() {
        return item.getValue() != null && item.getValue().id > 0;
    }
}
