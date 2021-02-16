package com.neudesic.pmugfebappend.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.neudesic.pmugfebappend.model.Person;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Person.class}, version = 1, exportSchema = false)
@TypeConverters(Converter.class)
public abstract class PersonDatabase extends RoomDatabase {

    abstract PersonDao personDao();

    private static volatile PersonDatabase INSTANCE;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(4);

    public static PersonDatabase getDatabase(Context context) {
        PersonDatabase temp = INSTANCE;
        if (temp != null) {
            return temp;
        }

        synchronized (PersonDatabase.class) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    PersonDatabase.class,
                    "person_table")
                    .build();
        }
        return INSTANCE;
    }

}
