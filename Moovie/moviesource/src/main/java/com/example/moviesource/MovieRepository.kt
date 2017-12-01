package com.example.moviesource

import com.example.moviesource.entities.Movie
import com.example.moviesource.source.MovieDataSource
import com.example.moviesource.source.MovieLocalCache
import com.example.moviesource.source.MovieRemoteSource
import io.reactivex.Single

/**
 * Created by xijun on 29/11/2017.
 */

class MovieRepository(private var cache: MovieLocalCache, private var remote: MovieRemoteSource) : MovieDataSource {

    override fun getMovie(movieID: String): Single<Movie> {
        return cache.getMovie(movieID).onErrorResumeNext { remote.getMovie(movieID) }
    }

    override fun getMovies(): Single<List<Movie>> {
       // return Single.concat(cache.getMovies(), remote.getMovies()).firstOrError()
        return remote.getMovies()
    }

}