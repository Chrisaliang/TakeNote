package com.chris.eban.presenter.event;

import com.chris.eban.domain.Result;
import com.chris.eban.domain.usecase.EventSaveInsert;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import timber.log.Timber;

public class EventDetailViewModel extends ViewModel implements SingleObserver<Result<Boolean>> {

    private static final String TAG = "EventDetailViewModel";

    public final MutableLiveData<EventItem> item = new MutableLiveData<>();
    //    private EventItem item;
    private EventSaveInsert eventSaveInsert;
    private Disposable disposable;

    public EventDetailViewModel(EventSaveInsert eventSaveInsert) {
        this.eventSaveInsert = eventSaveInsert;
    }

    public void setItem(EventItem item) {
        this.item.setValue(item);
    }

    void saveEvent() {


        eventSaveInsert.setItem(item.getValue());

        eventSaveInsert.execute().subscribe(this);

    }

    @Override
    public void onSubscribe(Disposable d) {
        checkDisposable();
        disposable = d;
    }

    @Override
    public void onSuccess(Result<Boolean> booleanResult) {

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
