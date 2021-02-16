package com.neudesic.pmug02_16_2021_kotlin

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //set a clicklistener to show a toast when this specific button is clicked
        val showToast = findViewById<Button>(R.id.action_show_toast)
        showToast.setOnClickListener {
            //TODO show a toast
        }

        //set a clicklistener to show a snackbar when this button is clicked
        val showSnackbar = findViewById<Button>(R.id.action_show_snackbar)
        showSnackbar.setOnClickListener {
            //TODO show a snackbar
        }

        //set a clicklistener to show a notification when this button is clicked
        val showNotification = findViewById<Button>(R.id.action_show_notification)
        showNotification.setOnClickListener {
            //Notifications are ONLY available on android phones oreo (8) and up
            //TODO show a notification
        }

        //Start a one time job when this button is clicked -- have it show a notification when done
        val oneTimeJob = findViewById<Button>(R.id.action_start_job)
        oneTimeJob.setOnClickListener {
            //TODO start a one time job
        }

        //Start a periodic job that fires off every minute -- have it show a notification and write to logcat
        val periodicJob = findViewById<Button>(R.id.action_start_periodic_job)
        periodicJob.setOnClickListener {
            //TODO start a periodic job
        }

        val navigate = findViewById<Button>(R.id.action_navigate)
        navigate.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
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