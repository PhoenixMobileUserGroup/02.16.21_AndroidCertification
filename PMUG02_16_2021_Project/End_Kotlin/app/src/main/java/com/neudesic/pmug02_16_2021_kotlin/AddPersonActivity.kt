package com.neudesic.pmug02_16_2021_kotlin

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import com.neudesic.pmug02_16_2021_kotlin.viewmodel.PersonViewModel

class AddPersonActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_person)
        val viewModel: PersonViewModel = ViewModelProvider(this, object : AndroidViewModelFactory(
            application
        ) {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                val personViewModel = PersonViewModel(application)
                return personViewModel as T
            }
        }).get(PersonViewModel::class.java)
        val name = findViewById<EditText>(R.id.name)
        val age = findViewById<EditText>(R.id.age)
        val birthday = findViewById<EditText>(R.id.birthday)
        val addPerson = findViewById<Button>(R.id.action_add)
        addPerson.setOnClickListener { v: View? ->
            viewModel.addPerson(
                name.text.toString(),
                age.text.toString().toInt(),
                birthday.text.toString()
            )
            Toast.makeText(applicationContext, "Person added!", Toast.LENGTH_LONG).show()
        }
    }
}
