package com.neudesic.pmug02_16_2021_kotlin.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.neudesic.pmug02_16_2021_kotlin.model.Person

@Database(entities = [Person::class], version = 1, exportSchema = false)
@TypeConverters(Converter::class)
abstract class PersonDatabase: RoomDatabase() {
}