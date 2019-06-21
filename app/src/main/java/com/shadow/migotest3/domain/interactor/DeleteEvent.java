package com.shadow.migotest3.domain.interactor;

import com.shadow.migotest3.domain.model.Event;
import com.shadow.migotest3.domain.repository.EventRepository;
import com.shadow.migotest3.domain.repository.db.DbRepository;

import io.reactivex.Completable;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

public class DeleteEvent {
    private final Event event;
    private final EventRepository eventRepository;

    public DeleteEvent(Event event, EventRepository eventRepository) {

        this.event = event;
        this.eventRepository = eventRepository;
    }

    public Completable execute() {
        return eventRepository.deleteEvents(event).subscribeOn(Schedulers.io());
    }
}
