package com.neudesic.pmugfebappend.database;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.neudesic.pmugfebappend.model.Person;

import java.util.List;

public class PersonRepository {

    private PersonDao personDao;

    public PersonRepository(Application application) {
        PersonDatabase database = PersonDatabase.getDatabase(application);
        personDao = database.personDao();
    }

    public LiveData<List<Person>> getAllPeople() {
        return personDao.getAllPerson();
    }

    public void insert(Person person) {
        personDao.insert(person);
    }
}
