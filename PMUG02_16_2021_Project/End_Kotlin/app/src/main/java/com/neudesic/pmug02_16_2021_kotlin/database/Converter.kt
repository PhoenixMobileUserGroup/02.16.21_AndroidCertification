package com.neudesic.pmug02_16_2021_kotlin.database

import androidx.room.TypeConverter
import org.joda.time.LocalDate

class Converter {
    @TypeConverter
    fun fromLongToLocalDate(long: Long) : LocalDate {
        return LocalDate(long)
    }
    @TypeConverter
    fun toLongFromLocalDate(localDate: LocalDate) : Long {
        return localDate.toDate().time
    }
}