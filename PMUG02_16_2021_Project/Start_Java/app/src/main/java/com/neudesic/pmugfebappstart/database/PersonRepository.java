package com.neudesic.pmugfebappstart.database;

public class PersonRepository {

    private PersonDao personDao;

    public PersonRepository(PersonDao personDao) {
        this.personDao = personDao;
    }
}
