package com.shadow.migotest3.domain.repository.db;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.shadow.migotest3.domain.model.Event;
import com.shadow.migotest3.domain.repository.EventRepository;

import org.threeten.bp.LocalDateTime;

import io.reactivex.Completable;
import io.reactivex.schedulers.Schedulers;

public class DbRepository implements EventRepository {

    private final EventDao eventDao;

    public DbRepository(Context context) {
        EventDatabase database = EventDatabase.getDatabase(context);
        eventDao = database.eventDao();
        Completable.fromAction(() -> eventDao.setEvent(createFakeEvent())).subscribeOn(Schedulers.io()).subscribe();
    }

    private EventEntity createFakeEvent() {
        EventEntity eventEntity = new EventEntity();
        eventEntity.title = "test";
        eventEntity.description = "test";
        return eventEntity;
    }

    @Override
    public LiveData<PagedList<Event>> getEvents() {
        return new LivePagedListBuilder<>(eventDao.getEvents().map(this::toEvents), 20).build();
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
