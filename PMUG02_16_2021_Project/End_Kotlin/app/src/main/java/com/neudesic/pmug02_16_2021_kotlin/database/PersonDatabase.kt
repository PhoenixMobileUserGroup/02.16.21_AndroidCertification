package com.neudesic.pmug02_16_2021_kotlin.database

import android.content.Context
import androidx.room.*
import com.neudesic.pmug02_16_2021_kotlin.model.Person

@Database(entities = [Person::class], version = 1, exportSchema = false)
@TypeConverters(Converter::class)
abstract class PersonDatabase : RoomDatabase() {
    abstract fun personDao(): PersonDao

    companion object {
        @Volatile
        private var INSTANCE: PersonDatabase? = null

        fun getDatabase(context: Context): PersonDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PersonDatabase::class.java,
                    "person_table"
                ).build()

                INSTANCE = instance
                return instance
            }
        }
    }
}