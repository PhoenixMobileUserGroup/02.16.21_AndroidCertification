package com.neudesic.pmugfebappstart.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.neudesic.pmugfebappstart.model.Person;

import java.util.List;

public class PersonViewModel extends AndroidViewModel {
    private final LiveData<List<Person>> personsData = new MutableLiveData<>();

    public PersonViewModel(@NonNull Application application) {
        super(application);

        //instantiate the room database here
    }

    public LiveData<List<Person>> getData() {
        return personsData;
    }

    public void addPerson(String name, int age, String birthday) {
        //add method
    }
}
