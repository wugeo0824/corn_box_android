package com.example.moviesource.source

import com.example.moviesource.entities.Movie
import io.reactivex.Single

class MovieLocalCache: MovieDataSource {

    private val sample: Movie = Movie(1, "Blade Runner", "How can this movie last 3 hours long?", "https://image.tmdb.org/t/p/w342/vlc95gl3PtrjxSEuM8RhTtSm2xU.jpg")

    override fun getMovie(movieID: String): Single<Movie> {
        return Single.just(sample)
    }

    override fun getMovies(): Single<List<Movie>> {
        val list: ArrayList<Movie> = ArrayList()
        list.add(sample)
        list.add(sample)
        list.add(sample)
        list.add(sample)
        list.add(sample)
        list.add(sample)
        return Single.just(list)
    }
}