package com.neudesic.pmug02_16_2021_kotlin.database

import android.app.Application
import androidx.lifecycle.LiveData
import com.neudesic.pmug02_16_2021_kotlin.model.Person

class PersonRepository(application: Application) {
    private val personDao: PersonDao
    val allPeople: LiveData<List<Person>>
        get() = personDao.getAllPerson()

    suspend fun insert(person: Person) {
        personDao.insert(person)
    }

    init {
        val database = PersonDatabase.getDatabase(application)
        personDao = database.personDao()
    }
}
