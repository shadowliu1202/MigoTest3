package com.shadow.migotest3.domain.repository.db;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.shadow.migotest3.domain.model.Event;

import org.threeten.bp.LocalDateTime;

@Entity
public class EventEntity {
    @PrimaryKey(autoGenerate = true)
    public Integer id;
    @NonNull
    public String title = "";
    @NonNull
    public String description = "";
    @NonNull
    public String startDate = LocalDateTime.now().toString();
    @NonNull
    public String endDate = LocalDateTime.now().toString();
    @NonNull
    public String category = Event.Category.Others.name();
}
