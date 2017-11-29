package com.example.moviesource.source

import com.example.moviesource.entities.Movie
import io.reactivex.Single

interface MovieDataSource {

    fun getMovie(movieID: String): Single<Movie>

    fun getMovies(): Single<List<Movie>>

}