package com.example.moviesource

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import com.example.moviesource.daos.BookmarkedDao
import com.example.moviesource.daos.MovieDao
import com.example.moviesource.entities.BookmarkedEntry
import com.example.moviesource.entities.Movie
import com.example.moviesource.utils.RoomTypeConverter

@Database(
        entities = [(Movie::class), (BookmarkedEntry::class)],
        version = 1)
@TypeConverters(RoomTypeConverter::class)
abstract class MovieDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao

    abstract fun bookmarkedDao(): BookmarkedDao
}
