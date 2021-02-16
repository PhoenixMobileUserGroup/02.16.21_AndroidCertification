package com.neudesic.pmugfebappend;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.neudesic.pmugfebappend.worker.OneTimeWorker;
import com.neudesic.pmugfebappend.worker.PeriodicWorker;

import java.util.concurrent.TimeUnit;

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
            Toast.makeText(this, "This is a sample toast!", Toast.LENGTH_LONG).show();
        });

        //set a clicklistener to show a snackbar when this button is clicked
        Button showSnackbar = findViewById(R.id.action_show_snackbar);
        showSnackbar.setOnClickListener(v -> {
            Snackbar.make(showSnackbar, "This is a sample snackbar!", Snackbar.LENGTH_LONG).show();
        });

        //set a clicklistener to show a notification when this button is clicked
        Button showNotification = findViewById(R.id.action_show_notification);
        showNotification.setOnClickListener(v -> {
            NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "NewChannel")
                    .setSmallIcon(R.drawable.ic_baseline_emoji_emotions_24)
                    .setContentTitle("This is my notification")
                    .setContentText("Testing notifications! Hello, World!")
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT);

            createNotificationChannel();

            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

            // notificationId is a unique int for each notification that you must define
            notificationManager.notify(1, builder.build());
        });

        //Start a one time job when this button is clicked -- have it show a notification when done
        Button oneTimeJob = findViewById(R.id.action_start_job);
        oneTimeJob.setOnClickListener(v -> {
            OneTimeWorkRequest workRequest = new OneTimeWorkRequest.Builder(OneTimeWorker.class)
                    .build();

            WorkManager.getInstance(this).enqueue(workRequest);
        });

        //Start a periodic job that fires off every 15 minutes -- have it show a notification and write to logcat
        Button periodicJob = findViewById(R.id.action_start_periodic_job);
        periodicJob.setOnClickListener(v -> {
            PeriodicWorkRequest workRequest = new PeriodicWorkRequest.Builder(PeriodicWorker.class, 15, TimeUnit.MINUTES).build();
            WorkManager.getInstance(this).enqueueUniquePeriodicWork("MyWork", ExistingPeriodicWorkPolicy.KEEP, workRequest);
        });

        Button cancelWork = findViewById(R.id.action_cancel_Work);
        cancelWork.setOnClickListener(v -> {
            WorkManager.getInstance(this).cancelAllWork();
        });

        Button navigate = findViewById(R.id.action_navigate);
        navigate.setOnClickListener(v -> {
            Intent intent = new Intent(this, SecondActivity.class);
            startActivity(intent);
        });
    }

    //Directly from Google: https://developer.android.com/training/notify-user/build-notification#java
    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("NewChannel", name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
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