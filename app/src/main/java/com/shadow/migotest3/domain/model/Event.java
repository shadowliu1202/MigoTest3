package com.shadow.migotest3.domain.model;

import android.content.Intent;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;

import com.google.auto.value.AutoValue;

import org.jetbrains.annotations.NotNull;
import org.threeten.bp.LocalDateTime;

@AutoValue
public abstract class Event implements Comparable<Event>, Parcelable {

    public final static DiffUtil.ItemCallback<Event> DIFF_CALLBACK = new DiffUtil.ItemCallback<Event>() {
        @Override
        public boolean areItemsTheSame(Event oldItem, @NotNull Event newItem) {
            return oldItem.compareTo(newItem) == 0;
        }

        @Override
        public boolean areContentsTheSame(Event oldItem, @NotNull Event newItem) {
            return oldItem.compareTo(newItem) == 0;
        }
    };

    public enum Category {
        Personal, Business, Others
    }

    @Nullable
    public abstract Integer id();

    public abstract String title();

    public abstract String description();

    public abstract LocalDateTime startDateTime();

    public abstract LocalDateTime endDateTime();

    public abstract Category category();

    public abstract int order();

    @AutoValue.Builder
    public abstract static class Builder {

        public abstract Builder title(String title);

        public abstract Builder description(String description);

        public abstract Builder startDateTime(LocalDateTime startDateTime);

        public abstract Builder endDateTime(LocalDateTime endDateTime);

        public abstract Builder id(Integer id);

        public abstract Builder category(Category category);

        public abstract Builder order(int order);

        public abstract Event build();
    }

    @Override
    public int compareTo(@NonNull Event o) {
        return this.id().compareTo(o.id());
    }

    public static Builder builder() {
        return new AutoValue_Event.Builder();
    }
}
