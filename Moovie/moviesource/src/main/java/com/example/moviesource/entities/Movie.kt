package com.example.moviesource.entities

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Index
import android.arch.persistence.room.PrimaryKey
import android.os.Parcel
import android.os.Parcelable

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
        @ColumnInfo(name = "poster_url") val posterUrl: String) : Parcelable {

    constructor(parcel: Parcel) : this(
            parcel.readValue(Long::class.java.classLoader) as? Long,
            parcel.readInt(),
            parcel.readString(),
            parcel.readDouble(),
            parcel.readString(),
            parcel.readString())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
        parcel.writeInt(tmdbId)
        parcel.writeString(title)
        parcel.writeDouble(averageVote)
        parcel.writeString(description)
        parcel.writeString(posterUrl)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Movie> {
        override fun createFromParcel(parcel: Parcel): Movie {
            return Movie(parcel)
        }

        override fun newArray(size: Int): Array<Movie?> {
            return arrayOfNulls(size)
        }
    }
}
