package com.shadow.migotest3.domain.repository.db;

import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface EventDao {

    @Query("SELECT * from EventEntity")
    DataSource.Factory<Integer, EventEntity> getEvents();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void setEvent(EventEntity eventEntity);

    @Delete
    void deleteEvent(EventEntity eventEntity);
}
