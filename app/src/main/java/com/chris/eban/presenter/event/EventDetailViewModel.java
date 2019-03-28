package com.chris.eban.presenter.event;

import com.chris.eban.domain.Result;
import com.chris.eban.domain.usecase.EventSaveInsert;
import com.chris.eban.domain.usecase.EventSaveUpdate;

import androidx.lifecycle.ViewModel;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

public class EventDetailViewModel extends ViewModel implements SingleObserver<Result<Boolean>> {


    private EventSaveInsert eventSaveInsert;

    private EventSaveUpdate eventSaveUpdate;


    public EventDetailViewModel(EventSaveInsert eventSaveInsert, EventSaveUpdate eventSaveUpdate) {
        this.eventSaveInsert = eventSaveInsert;
        this.eventSaveUpdate = eventSaveUpdate;
    }

     void saveEvent() {
//        eventSaveInsert.setItem(eventItem);

        eventSaveInsert.execute().subscribe(this);
    }

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onSuccess(Result<Boolean> booleanResult) {

    }

    @Override
    public void onError(Throwable e) {

    }
}
