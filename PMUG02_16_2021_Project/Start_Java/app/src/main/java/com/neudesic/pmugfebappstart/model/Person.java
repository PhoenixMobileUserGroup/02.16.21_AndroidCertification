package com.neudesic.pmugfebappstart.model;

import java.time.LocalDate;

public class Person {
    private int age;
    private String name;
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
}
