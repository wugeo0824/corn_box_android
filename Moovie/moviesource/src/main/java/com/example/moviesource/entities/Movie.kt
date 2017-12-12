package com.example.moviesource.entities

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Index
import android.arch.persistence.room.PrimaryKey

@Entity(
        tableName = "movies",
        indices = [
            (Index(value = ["tmdb_id"], unique = true))
        ])
data class Movie(
        @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") var id: Long? = null,
        @ColumnInfo(name = "tmdb_id") var tmdbId: Int,
        @ColumnInfo(name = "title") var title: String,
        @ColumnInfo(name = "average_vote") val averageVote: Double,
        @ColumnInfo(name = "description") val description: String,
        @ColumnInfo(name = "poster_url") val posterUrl: String)
