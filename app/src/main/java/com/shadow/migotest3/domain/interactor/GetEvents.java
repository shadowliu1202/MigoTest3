package com.shadow.migotest3.domain.interactor;

import androidx.lifecycle.LiveData;
import androidx.paging.PagedList;

import com.shadow.migotest3.domain.model.Event;
import com.shadow.migotest3.domain.repository.EventRepository;

public class GetEvents {

    private EventRepository eventRepository;

    public GetEvents(EventRepository eventRepository) {

        this.eventRepository = eventRepository;
    }

    public LiveData<PagedList<Event>> execute() {
        return eventRepository.getEvents();
    }

}
