package com.example.moviesource.daos

import android.arch.persistence.room.*
import com.example.moviesource.entities.Movie
import io.reactivex.Single
import timber.log.Timber

@Dao
abstract class MovieDao {

    @Query("SELECT * FROM movies")
    abstract fun getMovies(): Single<List<Movie>>

    @Query("SELECT * FROM movies WHERE tmdb_id = :id")
    abstract fun getMovieWithTmdbId(id: Int): Single<Movie>

    @Query("SELECT * FROM movies WHERE tmdb_id = :id")
    abstract fun getMovieWithTmdbIdSync(id: Int): Movie?

    @Query("SELECT * FROM movies WHERE id = :id")
    abstract fun getMovieWithId(id: Long): Single<Movie>

    @Query("SELECT * FROM movies WHERE id = :id")
    abstract fun getMovieWithIdSync(id: Long): Movie?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    protected abstract fun insertMovie(movie: Movie): Long

    @Update
    protected abstract fun updateMovie(movie: Movie)

    fun insertOrUpdateShow(movie: Movie): Movie = when {
        movie.id == null -> {
            Timber.d("Inserting movie: %s", movie)
            movie.copy(id = insertMovie(movie))
        }
        else -> {
            Timber.d("Updating movie: %s", movie)
            updateMovie(movie)
            movie
        }
    }
}
