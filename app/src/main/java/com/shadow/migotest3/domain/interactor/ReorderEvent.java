package com.shadow.migotest3.domain.interactor;

import com.shadow.migotest3.domain.model.Event;
import com.shadow.migotest3.domain.repository.EventRepository;

import io.reactivex.Completable;
import io.reactivex.schedulers.Schedulers;

public class ReorderEvent {
    private EventRepository eventRepository;
    private Event event;

    public ReorderEvent(Event event, int toPos, EventRepository eventRepository) {
        this.eventRepository = eventRepository;
        this.event = Event.builder()
                .title(event.title())
                .description(event.description())
                .id(event.id())
                .startDateTime(event.startDateTime())
                .endDateTime(event.endDateTime())
                .order(toPos)
                .category(event.category())
                .build();
    }

    public Completable execute() {
        return eventRepository.setEvents(event).subscribeOn(Schedulers.io());
    }
}
