package com.neudesic.pmug02_16_2021_kotlin.worker

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.neudesic.pmug02_16_2021_kotlin.R

class PeriodicWorker(context: Context, workerParams: WorkerParameters) : Worker(context,
        workerParams
) {
    //This really does the same thing as a onetimework request
    override fun doWork(): Result {
        val builder = NotificationCompat.Builder(applicationContext, "PeriodicWorkRequest")
                .setSmallIcon(R.drawable.ic_baseline_emoji_emotions_24)
                .setContentTitle("This is my notification")
                .setContentText("From periodic time work request! Hello!")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        createNotificationChannel()
        val notificationManager = NotificationManagerCompat.from(applicationContext)

        // notificationId is a unique int for each notification that you must define
        notificationManager.notify(5, builder.build())
        return Result.success()
    }

    private fun createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name: CharSequence = applicationContext.getString(R.string.channel_name)
            val description = applicationContext.getString(R.string.channel_description)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel("PeriodicWorkRequest", name, importance)
            channel.description = description
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            val notificationManager = ContextCompat.getSystemService(applicationContext, NotificationManager::class.java)
            notificationManager!!.createNotificationChannel(channel)
        }
    }
}