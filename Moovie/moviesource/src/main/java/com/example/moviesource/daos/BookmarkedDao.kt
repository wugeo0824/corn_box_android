package com.example.moviesource.daos

import android.arch.persistence.room.*
import com.example.moviesource.entities.BookmarkedEntry
import io.reactivex.Single

@Dao
abstract class BookmarkedDao {

    @Transaction
    @Query("SELECT * FROM bookmarked_entities ORDER BY bookmarked_time DESC")
    abstract fun entries(): Single<List<BookmarkedEntry>>

    @Query("DELETE FROM bookmarked_entities")
    abstract fun deleteAll()

    @Query("SELECT * FROM bookmarked_entities WHERE show_id = :id")
    abstract fun entryWithShowId(id: Long): Single<BookmarkedEntry>

    @Query("SELECT * FROM bookmarked_entities WHERE show_id = :id")
    abstract fun entryWithShowIdSync(id: Long): BookmarkedEntry


    @Delete
    abstract fun delete(bookmark: BookmarkedEntry)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertBookmark(bookmark: BookmarkedEntry): Long

    @Update
    abstract fun updateBookmark(bookmark: BookmarkedEntry)
}
