package com.example.moviesource.daos

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import android.arch.persistence.room.Transaction
import com.example.moviesource.entities.BookmarkedEntry
import io.reactivex.Flowable

@Dao
abstract class BookmarkedDao {

    @Transaction
    @Query("SELECT * FROM bookmarked_entities ORDER BY bookmarked_time DESC")
    abstract fun entries(): Flowable<List<BookmarkedEntry>>

    @Query("DELETE FROM bookmarked_entities")
    abstract fun deleteAll()
}
