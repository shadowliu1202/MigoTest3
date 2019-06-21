package com.shadow.migotest3.domain.repository;

import androidx.lifecycle.LiveData;
import androidx.paging.PagedList;

import com.shadow.migotest3.domain.model.Event;

public interface EventRepository {
    LiveData<PagedList<Event>> getEvents();
}
