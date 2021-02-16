package com.neudesic.pmugfebappend.worker;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.neudesic.pmugfebappend.R;

import static androidx.core.content.ContextCompat.getSystemService;

public class PeriodicWorker extends Worker {

    public PeriodicWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    //This really does the same thing as a onetimework request
    @NonNull
    @Override
    public Result doWork() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), "PeriodicWorkRequest")
                .setSmallIcon(R.drawable.ic_baseline_emoji_emotions_24)
                .setContentTitle("This is my notification")
                .setContentText("From periodic time work request! Hello!")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        createNotificationChannel();

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getApplicationContext());

        // notificationId is a unique int for each notification that you must define
        notificationManager.notify(5, builder.build());

        return Result.success();

    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getApplicationContext().getString(R.string.channel_name);
            String description = getApplicationContext().getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("PeriodicWorkRequest", name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(getApplicationContext(), NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}
