package com.shadow.migotest3;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.jakewharton.threetenabp.AndroidThreeTen;
import com.shadow.migotest3.domain.interactor.GetEvents;
import com.shadow.migotest3.domain.model.Event;
import com.shadow.migotest3.domain.repository.db.DbRepository;
import com.shadow.migotest3.presentation.EventDetailActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.Objects;

public class MainActivity extends AppCompatActivity implements EventAdapter.onItemSelect {

    private EventAdapter adapters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        AndroidThreeTen.init(this);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            Intent intent = new Intent(this, EventDetailActivity.class);
            if (adapters.getCurrentList() == null || adapters.getItemCount() == 0 || adapters.getCurrentList().get(adapters.getItemCount() - 1) == null) {
                intent.putExtra(EventDetailActivity.ORDER, 1);
            } else {
                int order = adapters.getCurrentList().get(adapters
                        ..getItemCount() - 1).order() + 1;
                intent.putExtra(EventDetailActivity.ORDER, order);
            }
            startActivity(intent);
        });
        RecyclerView rv_content = findViewById(R.id.rv_content);
        rv_content.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));

        adapters = new EventAdapter(this);
        rv_content.setAdapter(adapters);
        new GetEvents(new DbRepository(this)).execute().observe(this, adapters::submitList);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.Callback() {
            @Override
            public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
                int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
                int swipeFlags = 0;
                return makeMovementFlags(dragFlags, swipeFlags);
            }

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                final int fromPos = viewHolder.getAdapterPosition();
                final int toPos = target.getAdapterPosition();
                // move item in `fromPos` to `toPos` in adapter.
                return true;// true if moved, false otherwise
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                adapters.notifyDataSetChanged();
            }


        });
        itemTouchHelper.attachToRecyclerView(rv_content);
    }

    @Override
    public void onSelect(Event event) {
        Intent intent = new Intent(this, EventDetailActivity.class);
        intent.putExtra(EventDetailActivity.EVENT, event);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
