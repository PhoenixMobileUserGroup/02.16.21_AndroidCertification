package com.neudesic.pmug02_16_2021_kotlin.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.neudesic.pmug02_16_2021_kotlin.database.Converter
import org.joda.time.LocalDate


@Entity(tableName = "person_table")
class Person(
        val age: Int,
        val name: String,
        @TypeConverters(Converter::class)
        val birthday: LocalDate
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}