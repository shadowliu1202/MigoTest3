package com.shadow.migotest3.domain.interactor;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.PagedList;

import com.shadow.migotest3.domain.model.Event;

import io.reactivex.Flowable;

public class GetEvents {

    public LiveData<PagedList<Event>> execute() {
        return new MutableLiveData<>();
    }

}
