package com.neudesic.pmug02_16_2021_kotlin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.neudesic.pmug02_16_2021_kotlin.viewmodel.PersonViewModel

class SecondActivity : AppCompatActivity() {
    private lateinit var recycler: RecyclerView
    private lateinit var floatingActionButton: FloatingActionButton

    private lateinit var viewModel: PersonViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        recycler = findViewById(R.id.recycler)
        floatingActionButton = findViewById(R.id.fab)


    }
}