package com.neudesic.pmug02_16_2021_kotlin

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.neudesic.pmug02_16_2021_kotlin.model.Person
import com.neudesic.pmug02_16_2021_kotlin.viewmodel.PersonViewModel

class SecondActivity : AppCompatActivity() {
    var recyclerView: RecyclerView? = null
    var adapter: PersonAdapter? = null
    var floatingActionButton: FloatingActionButton? = null

    var viewModel: PersonViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        recyclerView = findViewById(R.id.recycler)
        floatingActionButton = findViewById(R.id.fab)
        floatingActionButton!!.setOnClickListener { v: View? ->
            val intent = Intent(this, AddPersonActivity::class.java)
            startActivity(intent)
        }
        viewModel = ViewModelProvider(this, object : AndroidViewModelFactory(application) {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                val personViewModel = PersonViewModel(application)
                return personViewModel as T
            }
        })[PersonViewModel::class.java]
        viewModel!!.data.observe(this) { people: List<Person> ->
            this.onDataReceived(people)
        }
    }

    private fun onDataReceived(people: List<Person>) {
        if (adapter == null) {
            adapter = PersonAdapter(people)
            recyclerView!!.layoutManager = LinearLayoutManager(this)
            recyclerView!!.adapter = adapter
        } else {
            adapter!!.update(people)
            adapter!!.notifyDataSetChanged()
        }
    }
}