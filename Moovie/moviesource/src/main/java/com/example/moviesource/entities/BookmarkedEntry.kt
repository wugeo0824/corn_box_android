package com.example.moviesource.entities

import android.arch.persistence.room.*

@Entity(
        tableName = "bookmarked_entities",
        indices = [(Index(value = ["show_id"], unique = true))],
        foreignKeys = [
            ForeignKey(entity = Movie::class,
                    parentColumns = arrayOf("id"),
                    childColumns = arrayOf("show_id"),
                    onUpdate = ForeignKey.CASCADE,
                    onDelete = ForeignKey.CASCADE)
        ]
)
data class BookmarkedEntry(
        @PrimaryKey(autoGenerate = true) override val id: Long? = null,
        @ColumnInfo(name = "show_id") override val showId: Long,
        @ColumnInfo(name = "bookmarked_time") val lastWatched: Long
) : Entry
