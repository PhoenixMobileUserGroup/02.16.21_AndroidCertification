package com.neudesic.pmug02_16_2021_kotlin

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.OneTimeWorkRequest
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.google.android.material.snackbar.Snackbar
import com.neudesic.pmug02_16_2021_kotlin.worker.OneTimeWorker
import com.neudesic.pmug02_16_2021_kotlin.worker.PeriodicWorker
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //set a clicklistener to show a toast when this specific button is clicked
        val showToast = findViewById<Button>(R.id.action_show_toast)
        showToast.setOnClickListener {
            Toast.makeText(this, "This is a sample toast!", Toast.LENGTH_LONG).show()
        }

        //set a clicklistener to show a snackbar when this button is clicked
        val showSnackbar = findViewById<Button>(R.id.action_show_snackbar)
        showSnackbar.setOnClickListener {
            Snackbar.make(showSnackbar, "This is a sample snackbar!", Snackbar.LENGTH_LONG).show()
        }

        //set a clicklistener to show a notification when this button is clicked
        val showNotification = findViewById<Button>(R.id.action_show_notification)
        showNotification.setOnClickListener {
            val builder = NotificationCompat.Builder(this, "NewChannel")
                .setSmallIcon(R.drawable.ic_baseline_emoji_emotions_24)
                .setContentTitle("This is my notification")
                .setContentText("Testing notifications! Hello, World!")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)

            createNotificationChannel()

            val notificationManager = NotificationManagerCompat.from(this)

            // notificationId is a unique int for each notification that you must define

            // notificationId is a unique int for each notification that you must define
            notificationManager.notify(1, builder.build())
        }

        //Start a one time job when this button is clicked -- have it show a notification when done
        val oneTimeJob = findViewById<Button>(R.id.action_start_job)
        oneTimeJob.setOnClickListener {
            val workRequest = OneTimeWorkRequest.Builder(OneTimeWorker::class.java)
                .build()

            WorkManager.getInstance(this).enqueue(workRequest)
        }

        //Start a periodic job that fires off every minute -- have it show a notification and write to logcat
        val periodicJob = findViewById<Button>(R.id.action_start_periodic_job)
        periodicJob.setOnClickListener {
            val workRequest =
                PeriodicWorkRequest.Builder(PeriodicWorker::class.java, 1, TimeUnit.MINUTES).build()
            WorkManager.getInstance(this)
                .enqueueUniquePeriodicWork("MyWork", ExistingPeriodicWorkPolicy.KEEP, workRequest)

        }

        val cancelWork = findViewById<Button>(R.id.action_cancel_Work)
        cancelWork.setOnClickListener {
            WorkManager.getInstance(this).cancelAllWork()
        }

        val navigate = findViewById<Button>(R.id.action_navigate)
        navigate.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
        }
    }

    private fun createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name: CharSequence = getString(R.string.channel_name)
            val description = getString(R.string.channel_description)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel("NewChannel", name, importance)
            channel.description = description
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            val notificationManager = getSystemService(NotificationManager::class.java)
            notificationManager!!.createNotificationChannel(channel)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId
        return if (id == R.id.action_settings) {
            true
        } else super.onOptionsItemSelected(item)
    }
}