package com.example.moviesource.tmdb

import com.example.base.RetryAfterTimeoutWithDelay
import com.example.base.RxSchedulers
import com.example.base.extensions.toRxSingle
import com.uwetrottmann.tmdb2.Tmdb
import io.reactivex.Completable
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Singleton

/**
 * Created by xijun on 29/11/2017.
 */


@Singleton
class TmdbMovieFetcher constructor(
        private val tmdb: Tmdb,
        private val schedulers: RxSchedulers
) {

    fun getPopularMovies(page: Int): Completable{
        return tmdb.moviesService().popular(page, "en-US").toRxSingle()
                .subscribeOn(schedulers.network)
                .retryWhen(RetryAfterTimeoutWithDelay(3, 1000, this::shouldRetry, schedulers.network))
                .toCompletable()

    }

    private fun shouldRetry(throwable: Throwable): Boolean = when (throwable) {
        is HttpException -> throwable.code() == 429
        is IOException -> true
        is IllegalStateException -> true
        else -> false
    }
}