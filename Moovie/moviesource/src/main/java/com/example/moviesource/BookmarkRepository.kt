package com.example.moviesource

import com.example.base.RxSchedulers
import com.example.moviesource.daos.BookmarkedDao
import com.example.moviesource.daos.MovieDao
import com.example.moviesource.entities.BookmarkedEntry
import com.example.moviesource.entities.Movie
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import timber.log.Timber
import java.util.*

class BookmarkRepository constructor(
        private val bookmarkedDao: BookmarkedDao,
        private val movieDao: MovieDao,
        private val schedulers: RxSchedulers
) {
    private val calendar = Calendar.getInstance(TimeZone.getDefault())

    fun addBookmark(movie: Movie): Completable {
        return Completable.fromAction {
            val bookmarked = BookmarkedEntry(null, movie.id!!, calendar.timeInMillis)
            bookmarkedDao.insertBookmark(bookmarked)
            Timber.d("Added to bookmark")
        }
                .subscribeOn(schedulers.data)
    }

    fun removeBookmark(movie: Movie): Completable {
        return Completable.fromAction {
            movie.id?.apply {
                val bookmarked = bookmarkedDao.entryWithShowIdSync(movie.id!!)
                bookmarkedDao.delete(bookmarked)
            }
        }
                .subscribeOn(schedulers.data)
    }

    fun getBookmarks(): Single<List<Movie>> {
        return bookmarkedDao.entries()
                .map { bookmarkList ->
                    Timber.d("entries list size ${bookmarkList.size}")
                    val movieList = ArrayList<Movie>()
                    bookmarkList.forEach { bookmark ->
                        val movie = mapToMovie(bookmark)
                        movie?.apply {
                            movieList.add(movie)
                        }
                    }
                    return@map movieList
                }
    }

    private fun mapToMovie(bookmarkedEntry: BookmarkedEntry): Movie? {
        return movieDao.getMovieWithIdSync(bookmarkedEntry.showId)
    }
}
