package com.shadow.migotest3;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.shadow.migotest3.domain.interactor.GetEvents;
import com.shadow.migotest3.domain.model.Event;
import com.shadow.migotest3.domain.repository.db.DbRepository;
import com.shadow.migotest3.presentation.EventDetailActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity implements EventAdapter.onItemSelect {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> startActivity(new Intent(view.getContext(), EventDetailActivity.class)));
        RecyclerView rv_content = findViewById(R.id.rv_content);
        rv_content.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        EventAdapter adapters = new EventAdapter(this);
        rv_content.setAdapter(adapters);
        new GetEvents(new DbRepository(this)).execute().observe(this, adapters::submitList);
    }

    @Override
    public void onSelect(Event event) {
        Intent intent = new Intent(this, EventDetailActivity.class);
        intent.putExtra(EventDetailActivity.EVENT, event);
        startActivity(intent);
    }
}
