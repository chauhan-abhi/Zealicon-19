package com.jss.abhi.zealicon.activities;

import android.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.jss.abhi.zealicon.R;
import com.jss.abhi.zealicon.model.EventData;

import static com.jss.abhi.zealicon.utils.TitleCaseConverter.toTitleCase;

public class EventRegistrationActivity extends AppCompatActivity {
    private EventData eventData;
    private TextView eventName;
    private EditText zealIdView, contactView;
    private String eventTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_registration);


        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar_event_registration);
        setSupportActionBar(toolbar);
       // getSupportActionBar().setDisplayHomeAsUpEnabled(toolbar);

        if (getIntent() != null) {
            eventData = (EventData) getIntent().getSerializableExtra("eventData");
        }
        eventName = findViewById(R.id.event_name);
        zealIdView = findViewById(R.id.zeal_id);
        contactView = findViewById(R.id.zeal_id_contact_no);

        eventName.setText(toTitleCase(eventData.getName()));
    }
}
