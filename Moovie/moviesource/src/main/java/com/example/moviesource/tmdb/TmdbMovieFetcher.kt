package com.example.moviesource.tmdb

import android.util.Log
import com.example.base.RetryAfterTimeoutWithDelay
import com.example.base.RxSchedulers
import com.example.base.extensions.toRxSingle
import com.example.moviesource.entities.Movie
import com.uwetrottmann.tmdb2.Tmdb
import io.reactivex.Single
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by xijun on 29/11/2017.
 */


@Singleton
class TmdbMovieFetcher @Inject constructor(
        private val tmdb: Tmdb,
        private val schedulers: RxSchedulers
) {

    fun getPopularMovies(page: Int): Single<List<Movie>> {
        return tmdb.moviesService().popular(page, "en-US").toRxSingle()
                .subscribeOn(schedulers.network)
                .retryWhen(RetryAfterTimeoutWithDelay(3, 1000, this::shouldRetry, schedulers.network))
                .map { resultsPage ->
                    val converted = ArrayList<Movie>()

                    if (resultsPage.results != null) {
                        resultsPage.results.forEach { it ->
                            val movie = Movie(it.id, it.title, it.overview)
                            converted.add(movie)
                        }
                    }

                    return@map converted
                }
    }

    private fun shouldRetry(throwable: Throwable): Boolean = when (throwable) {
        is HttpException -> throwable.code() == 429
        is IOException -> true
        is IllegalStateException -> true
        else -> false
    }

}