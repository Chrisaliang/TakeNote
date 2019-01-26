package com.chris.eban.presenter;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class EventListViewModel extends ViewModel {

    public final MutableLiveData<Boolean> hasData = new MutableLiveData<>(false);
}
