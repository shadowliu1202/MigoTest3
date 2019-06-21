package com.shadow.migotest3.presentation;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.github.florent37.singledateandtimepicker.dialog.SingleDateAndTimePickerDialog;
import com.shadow.migotest3.R;
import com.shadow.migotest3.domain.interactor.DeleteEvent;
import com.shadow.migotest3.domain.interactor.SaveEvent;
import com.shadow.migotest3.domain.model.Event;
import com.shadow.migotest3.domain.repository.db.DbRepository;

import org.threeten.bp.Instant;
import org.threeten.bp.LocalDateTime;
import org.threeten.bp.ZoneId;
import org.threeten.bp.format.DateTimeFormatter;

import io.reactivex.android.schedulers.AndroidSchedulers;

public class EventDetailActivity extends AppCompatActivity {
    public static final String EVENT = "event";
    private Integer eventID = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);
        Spinner spinner = findViewById(R.id.sp_category);
        spinner.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, Event.Category.values()));
        Event event = getIntent().getParcelableExtra(EVENT);
        if (event != null) {
            initUI(event);
            eventID = event.id();
        }
        findViewById(R.id.btn_save).setOnClickListener(v -> checkAndSave());
        findViewById(R.id.btn_start).setOnClickListener(v -> setStartTime());
        findViewById(R.id.btn_end).setOnClickListener(v -> setEndTime());
        findViewById(R.id.btn_delete).setOnClickListener(v -> deleteEvent());
        findViewById(R.id.btn_delete).setVisibility(eventID == null ? View.INVISIBLE : View.VISIBLE);
    }

    @SuppressLint("CheckResult")
    private void deleteEvent() {
        new DeleteEvent(createEvent(), new DbRepository(this)).execute()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::finish, Throwable::printStackTrace);
    }

    private void setStartTime() {
        new SingleDateAndTimePickerDialog.Builder(this)
                .title("Select Date and Time")
                .listener(date -> {
                    LocalDateTime localDateTime = Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime();
                    Button btn_start = findViewById(R.id.btn_start);
                    btn_start.setText(localDateTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
                }).display();
    }

    private void setEndTime() {
        new SingleDateAndTimePickerDialog.Builder(this)
                .title("Select Date and Time")
                .listener(date -> {
                    LocalDateTime localDateTime = Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime();
                    Button btn_end = findViewById(R.id.btn_end);
                    btn_end.setText(localDateTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
                }).display();
    }

    @SuppressLint("CheckResult")
    private void checkAndSave() {
        if (checkContentEmpty()) {
            Toast.makeText(this, "Some data are empty", Toast.LENGTH_SHORT).show();
            return;
        }
        new SaveEvent(createEvent(), new DbRepository(this)).execute()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::finish, Throwable::printStackTrace);

    }

    private Event createEvent() {
        TextView et_title = findViewById(R.id.et_title);
        TextView et_description = findViewById(R.id.et_description);
        Button btn_start = findViewById(R.id.btn_start);
        Button btn_end = findViewById(R.id.btn_end);
        Spinner spinner = findViewById(R.id.sp_category);
        return Event.builder().title(et_title.getText().toString())
                .id(eventID)
                .description(et_description.getText().toString())
                .startDateTime(LocalDateTime.parse(btn_start.getText(), DateTimeFormatter.ISO_LOCAL_DATE_TIME))
                .endDateTime(LocalDateTime.parse(btn_end.getText(), DateTimeFormatter.ISO_LOCAL_DATE_TIME))
                .category(Event.Category.values()[spinner.getSelectedItemPosition()])
                .build();
    }

    private boolean checkContentEmpty() {
        TextView et_title = findViewById(R.id.et_title);
        TextView et_description = findViewById(R.id.et_description);
        Button btn_start = findViewById(R.id.btn_start);
        Button btn_end = findViewById(R.id.btn_end);
        return TextUtils.isEmpty(et_title.getText()) || TextUtils.isEmpty(et_description.getText())
                || "START".contentEquals(btn_start.getText()) || "END".contentEquals(btn_end.getText());
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
