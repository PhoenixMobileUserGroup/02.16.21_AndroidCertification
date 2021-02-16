package com.neudesic.pmugfebappend.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.neudesic.pmugfebappend.model.Person;

import java.util.List;

@Dao
public interface PersonDao {

    @Query("SELECT * FROM person_table")
    LiveData<List<Person>> getAllPerson();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Person person);
}
