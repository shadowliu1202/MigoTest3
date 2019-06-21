package com.shadow.migotest3.domain.interactor;

import com.shadow.migotest3.domain.model.Event;
import com.shadow.migotest3.domain.repository.EventRepository;

public class SaveEvent {
    private final Event event;
    private final EventRepository eventRepository;

    public SaveEvent(Event event, EventRepository eventRepository) {

        this.event = event;
        this.eventRepository = eventRepository;
    }

    public void execute() {

    }
}
