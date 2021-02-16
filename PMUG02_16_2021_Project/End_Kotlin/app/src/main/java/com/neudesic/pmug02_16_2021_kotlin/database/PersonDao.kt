package com.neudesic.pmug02_16_2021_kotlin.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.neudesic.pmug02_16_2021_kotlin.model.Person

@Dao
interface PersonDao {
    @Query("SELECT * FROM person_table")
    fun getAllPerson(): LiveData<List<Person>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(person: Person)

}