package com.neudesic.pmugfebappend.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.neudesic.pmugfebappend.database.PersonDatabase;
import com.neudesic.pmugfebappend.database.PersonRepository;
import com.neudesic.pmugfebappend.model.Person;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PersonViewModel extends AndroidViewModel {
    private PersonRepository repository;
    private static final ExecutorService executor =
            Executors.newFixedThreadPool(4);

    public PersonViewModel(@NonNull Application application) {
        super(application);

        //instantiate the room database here

        repository = new PersonRepository(application);
    }

    public LiveData<List<Person>> getData() {
        return repository.getAllPeople();
    }

    public void addPerson(String name, int age, String birthday) {
        DateTimeFormatter outFormat = DateTimeFormat.forPattern("dd-MM-yyyy");
        Person person = new Person(age, name, LocalDate.parse(birthday, outFormat));
        executor.execute(() -> repository.insert(person));
    }
}
