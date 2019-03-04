package com.jss.abhijeet.zealicon.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.jss.abhijeet.zealicon.R;

public class ZealIDActivity extends AppCompatActivity {

    private TextView zealidTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zeal_id);
        zealidTV = findViewById(R.id.zealIdTextView);
        zealidTV.setText("ZO_" + getIntent().getStringExtra("zealId"));
        findViewById(R.id.back_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                //nulify prev entries
            }
        });

    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
