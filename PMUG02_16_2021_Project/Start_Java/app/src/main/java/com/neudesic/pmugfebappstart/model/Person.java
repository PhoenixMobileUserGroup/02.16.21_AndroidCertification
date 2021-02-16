package com.neudesic.pmugfebappstart.model;


import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.neudesic.pmugfebappstart.database.Converter;

import org.joda.time.LocalDate;

@Entity(tableName = "person_table")
public class Person {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private int age;
    private String name;
    @TypeConverters(Converter.class)
    private LocalDate birthday;

    public Person(int age, String name, LocalDate birthday) {
        this.age = age;
        this.name = name;
        this.birthday = birthday;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
