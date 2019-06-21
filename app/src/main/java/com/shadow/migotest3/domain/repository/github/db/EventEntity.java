package com.shadow.migotest3.domain.repository.github.db;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class EventEntity {
    @PrimaryKey(autoGenerate = true)
    public int id;
}
