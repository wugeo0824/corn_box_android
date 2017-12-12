package com.example.moviesource.utils

import android.arch.persistence.room.TypeConverter
import java.sql.Date

object RoomTypeConverter {

    @TypeConverter
    @JvmStatic
    fun toDate(value: Long?): Date? {
        return value?.let {
            return Date(value)
        }
    }

    @TypeConverter
    @JvmStatic
    fun fromDate(date: Date?): Long? = date?.time
}
