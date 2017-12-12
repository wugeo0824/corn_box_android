package com.example.moviesource.source

import com.example.moviesource.daos.MovieDao
import com.example.moviesource.entities.Movie
import io.reactivex.Single

class MovieLocalCache constructor(private val movieDao: MovieDao) {

    fun getMovie(imdbId: Int): Single<Movie> {
        return movieDao.getMovieWithTmdbId(imdbId)
    }

    fun getMovies(): Single<List<Movie>> {
        return movieDao.getMovies()
    }
}
