package com.example.moviesource

import com.example.moviesource.entities.Movie
import com.example.moviesource.source.MovieLocalCache
import com.example.moviesource.source.MovieRemoteSource
import io.reactivex.Single

class MovieRepository(private var cache: MovieLocalCache, private var remote: MovieRemoteSource) {

    fun getMovie(tmdbId: Int): Single<Movie> {
        return cache.getMovie(tmdbId).onErrorResumeNext { remote.getMovie(tmdbId) }
    }

    fun getMovies(): Single<List<Movie>> {
        return Single.concat(cache.getMovies(), remote.getMovies()).firstOrError()
    }

    fun discoverMovies(): Single<List<Movie>> {
        return remote.discoverMovies()
    }

}
