package com.neudesic.pmugfebappend.database;

import androidx.room.TypeConverter;

import org.joda.time.LocalDate;


public class Converter {
    @TypeConverter
    public LocalDate fromLongToLocalDate(Long date) {
        return new LocalDate(date);
    }

    @TypeConverter
    public long toLongFromLocalDate(LocalDate localDate) {
        return localDate.toDate().getTime();
    }
}
