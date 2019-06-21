package com.shadow.migotest3.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.shadow.migotest3.R;
import com.shadow.migotest3.domain.model.Event;

import org.threeten.bp.format.DateTimeFormatter;

public class EventDetailActivity extends AppCompatActivity {

    public static final String EVENT = "event";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);
        Spinner spinner = findViewById(R.id.sp_category);
        spinner.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, Event.Category.values()));
        Event event = getIntent().getParcelableExtra(EVENT);
        if (event != null) {
            initUI(event);
        }
    }

    private void initUI(Event event) {
        TextView et_title = findViewById(R.id.et_title);
        TextView et_description = findViewById(R.id.et_description);
        et_title.setText(event.title());
        et_description.setText(event.description());
        Button btn_start = findViewById(R.id.btn_start);
        Button btn_end = findViewById(R.id.btn_end);
        btn_start.setText(event.startDateTime().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        btn_end.setText(event.endDateTime().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        Spinner spinner = findViewById(R.id.sp_category);
        spinner.setSelection(event.category().ordinal());
    }
}
