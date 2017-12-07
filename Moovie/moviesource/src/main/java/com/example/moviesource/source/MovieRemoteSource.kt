package com.example.moviesource.source

import com.example.moviesource.entities.Movie
import com.example.moviesource.tmdb.TmdbMovieFetcher
import io.reactivex.Single
import javax.inject.Inject

class MovieRemoteSource @Inject constructor(private val tmdbMovieFetcher: TmdbMovieFetcher) : MovieDataSource {

    override fun getMovie(movieID: String): Single<Movie> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getMovies(): Single<List<Movie>> {
        return tmdbMovieFetcher.getPopularMovies(1)
    }

    fun discoverMovies(): Single<List<Movie>> {
        return tmdbMovieFetcher.discover(1)
    }
}