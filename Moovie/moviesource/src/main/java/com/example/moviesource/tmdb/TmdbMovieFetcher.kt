package com.example.moviesource.tmdb

import com.example.base.RetryAfterTimeoutWithDelay
import com.example.base.RxSchedulers
import com.example.base.extensions.toRxSingle
import com.example.moviesource.daos.MovieDao
import com.example.moviesource.entities.Movie
import com.uwetrottmann.tmdb2.Tmdb
import com.uwetrottmann.tmdb2.entities.MovieResultsPage
import com.uwetrottmann.tmdb2.enumerations.SortBy
import io.reactivex.Single
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TmdbMovieFetcher @Inject constructor(
        private val tmdb: Tmdb,
        private val schedulers: RxSchedulers,
        private val movieDao: MovieDao
) {

    fun discover(page: Int): Single<List<Movie>> {
        return tmdb.discoverMovie()
                .includeAdult()
                .sort_by(SortBy.POPULARITY_DESC)
                .page(page)
                .build()
                .toRxSingle()
                .subscribeOn(schedulers.network)
                .retryWhen(RetryAfterTimeoutWithDelay(3, 1000, this::shouldRetry, schedulers.network))
                .map(this::mapToMovieEntity)
    }

    fun getPopularMovies(page: Int): Single<List<Movie>> {
        return tmdb.moviesService()
                .popular(page, "en-US")
                .toRxSingle()
                .subscribeOn(schedulers.network)
                .retryWhen(RetryAfterTimeoutWithDelay(3, 1000, this::shouldRetry, schedulers.network))
                .map(this::mapToMovieEntity)
    }

    private fun mapToMovieEntity(moviesResultsPage: MovieResultsPage): List<Movie> {
        val converted = ArrayList<Movie>()

        //TODO: refactor this, right now we are saving the movie every time
        moviesResultsPage.results?.let {
            it.forEach { tmdb ->
                val posterUrl = "https://image.tmdb.org/t/p/w342" + tmdb.poster_path
                val existingMovie: Movie? = movieDao.getMovieWithTmdbIdSync(tmdb.id)
                val movie = Movie(existingMovie?.id,
                        tmdb.id,
                        tmdb.title,
                        tmdb.vote_average,
                        tmdb.overview,
                        posterUrl)
                converted.add(movieDao.insertOrUpdateShow(movie))
            }
        }

        return converted
    }

    private fun shouldRetry(throwable: Throwable): Boolean = when (throwable) {
        is HttpException -> throwable.code() == 429
        is IOException -> true
        is IllegalStateException -> true
        else -> false
    }

}
