package com.neudesic.pmug02_16_2021_kotlin.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.neudesic.pmug02_16_2021_kotlin.database.PersonRepository
import com.neudesic.pmug02_16_2021_kotlin.model.Person
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.joda.time.LocalDate
import org.joda.time.format.DateTimeFormat
import java.util.concurrent.Executors

class PersonViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: PersonRepository = PersonRepository(application)
    val data: LiveData<List<Person>>
        get() = repository.allPeople

    fun addPerson(name: String?, age: Int, birthday: String?) {
        val outFormat = DateTimeFormat.forPattern("dd-MM-yyyy")
        val person = Person(
            age,
            name!!, LocalDate.parse(birthday, outFormat)
        )
        viewModelScope.launch(Dispatchers.IO) {
            repository.insert(person)
        }
    }

    companion object {
        private val executor = Executors.newFixedThreadPool(4)
    }

    init {

        //instantiate the room database here
    }
}
