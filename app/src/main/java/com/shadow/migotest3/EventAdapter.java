package com.shadow.migotest3;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.shadow.migotest3.domain.model.Event;


public class EventAdapter extends PagedListAdapter<Event, EventAdapter.EventView> {
    //
    private final onItemSelect itemSelect;

    EventAdapter(onItemSelect itemSelect) {
        super(Event.DIFF_CALLBACK);
        this.itemSelect = itemSelect;
    }

    interface onItemSelect {
        void onSelect(Event event);
    }

    @NonNull
    @Override
    public EventView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_item, parent, false);
        return new EventView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventView holder, int position) {
        holder.bind(getItem(position));
        holder.ivEdit.setOnClickListener(v -> itemSelect.onSelect(getItem(position)));
    }

    class EventView extends RecyclerView.ViewHolder {
        final TextView tvTitle;
        final TextView tvStartTime;
        final TextView tvEndTime;
        final TextView tvCategory;
        final ImageView ivEdit;

        EventView(View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvStartTime = itemView.findViewById(R.id.tv_start);
            tvEndTime = itemView.findViewById(R.id.tv_end);
            tvCategory = itemView.findViewById(R.id.tv_catagory);
            ivEdit = itemView.findViewById(R.id.iv_edit);
        }

        void bind(Event item) {
            tvTitle.setText(item.title());
            tvStartTime.setText(item.startDateTime().toString());
            tvEndTime.setText(item.endDateTime().toString());
            tvCategory.setText(item.category().name());
        }
    }
}
