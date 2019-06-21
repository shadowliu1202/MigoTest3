package com.shadow.migotest3.domain.model;

import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;

import com.google.auto.value.AutoValue;

import org.jetbrains.annotations.NotNull;
import org.threeten.bp.LocalDateTime;

import java.util.Objects;

@AutoValue
public abstract class Event implements Comparable<Event>, Parcelable {

    public final static DiffUtil.ItemCallback<Event> DIFF_CALLBACK = new DiffUtil.ItemCallback<Event>() {
        @Override
        public boolean areItemsTheSame(Event oldItem, @NotNull Event newItem) {
            return oldItem.compareTo(newItem) == 0;
        }

        @Override
        public boolean areContentsTheSame(Event oldItem, @NotNull Event newItem) {
            return oldItem.toString().equals(newItem.toString());
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

        public Builder addTitle(String title) {
            if (title.length() > 50) {
                throw new StringIndexOutOfBoundsException();
            }
            title(title);
            return this;
        }

        abstract Builder title(String title);

        public Builder addDescription(String title) {
            if (title.length() > 1000) {
                throw new StringIndexOutOfBoundsException();
            }
            description(title);
            return this;
        }

        abstract Builder description(String description);

        public abstract Builder startDateTime(LocalDateTime startDateTime);

        public abstract Builder endDateTime(LocalDateTime endDateTime);

        public abstract Builder id(Integer id);

        public abstract Builder category(Category category);

        public abstract Builder order(int order);

        public abstract Event build();
    }

    @Override
    public int compareTo(@NonNull Event o) {
        return Objects.requireNonNull(this.id()).compareTo(Objects.requireNonNull(o.id()));
    }

    public static Builder builder() {
        return new AutoValue_Event.Builder();
    }

    @NotNull
    @Override
    public String toString() {
        return title() + description() + startDateTime() + endDateTime() + category();
    }
}
