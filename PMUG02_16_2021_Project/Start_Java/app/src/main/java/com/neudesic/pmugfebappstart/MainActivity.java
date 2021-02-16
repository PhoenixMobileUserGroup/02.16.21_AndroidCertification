package com.neudesic.pmugfebappstart;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //set a clicklistener to show a toast when this specific button is clicked
        Button showToast = findViewById(R.id.action_show_toast);
        showToast.setOnClickListener(v -> {
            //TODO show a toast
        });

        //set a clicklistener to show a snackbar when this button is clicked
        Button showSnackbar = findViewById(R.id.action_show_snackbar);
        showSnackbar.setOnClickListener(v -> {
            //TODO show a snackbar
        });

        //set a clicklistener to show a notification when this button is clicked
        Button showNotification = findViewById(R.id.action_show_notification);
        showNotification.setOnClickListener(v -> {
            //Notifications are ONLY available on android phones oreo (8) and up
            //TODO show a notification
        });

        //Start a one time job when this button is clicked -- have it show a notification when done
        Button oneTimeJob = findViewById(R.id.action_start_job);
        oneTimeJob.setOnClickListener(v -> {
            //TODO start a one time job
        });

        //Start a periodic job that fires off every 30 seconds -- have it show a notification and write to logcat
        Button periodicJob = findViewById(R.id.action_start_periodic_job);
        periodicJob.setOnClickListener(v -> {
            //TODO start a periodic job
        });

        Button navigate = findViewById(R.id.action_navigate);
        navigate.setOnClickListener(v -> {
            Intent intent = new Intent(this, SecondActivity.class);
            startActivity(intent);
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}