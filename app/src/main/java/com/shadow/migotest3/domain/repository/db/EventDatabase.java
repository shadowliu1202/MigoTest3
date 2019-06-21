package com.shadow.migotest3.domain.repository.db;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import android.content.Context;

@Database(entities = {EventEntity.class}, version = 1)
public abstract class EventDatabase extends RoomDatabase {
    public abstract EventDao eventDao();

    private static EventDatabase INSTANCE;

    public static EventDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (EventDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            EventDatabase.class, "event_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
