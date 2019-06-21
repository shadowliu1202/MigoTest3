package com.shadow.migotest3.domain.repository.db;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.shadow.migotest3.domain.model.Event;
import com.shadow.migotest3.domain.repository.EventRepository;

import org.threeten.bp.LocalDateTime;

import io.reactivex.Completable;

public class DbRepository implements EventRepository {

    private final EventDao eventDao;

    public DbRepository(Context context) {
        EventDatabase database = EventDatabase.getDatabase(context);
        eventDao = database.eventDao();
    }

    @Override
    public LiveData<PagedList<Event>> getEvents() {
        return new LivePagedListBuilder<>(eventDao.getEvents().map(this::toEvents), 20).build();
    }

    @Override
    public Completable setEvents(Event event) {
        return Completable.fromAction(() -> eventDao.setEvent(toEventsEntity(event)));
    }

    private EventEntity toEventsEntity(Event event) {
        EventEntity entity = new EventEntity();
        entity.id = event.id();
        entity.title = event.title();
        entity.description = event.description();
        entity.startDate = event.startDateTime().toString();
        entity.endDate = event.endDateTime().toString();
        entity.category = event.category().name();
        return entity;
    }

    private Event toEvents(EventEntity eventEntity) {
        return Event.builder().id(eventEntity.id)
                .title(eventEntity.title)
                .description(eventEntity.description)
                .category(Event.Category.valueOf(eventEntity.category))
                .startDateTime(LocalDateTime.parse(eventEntity.startDate))
                .endDateTime(LocalDateTime.parse(eventEntity.endDate))
                .build();
    }
}
