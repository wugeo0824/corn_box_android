package crepe.dan.moovie.home.movies

import android.arch.lifecycle.MutableLiveData
import com.example.base.RxSchedulers
import com.example.moviesource.MovieRepository
import com.example.moviesource.entities.Movie
import crepe.dan.moovie.utils.RxAwareViewModel
import timber.log.Timber
import javax.inject.Inject

class MoviesViewModel @Inject constructor(
        private val schedulers: RxSchedulers,
        private val movieRepository: MovieRepository
) : RxAwareViewModel() {

    val liveMovieList = MutableLiveData<List<Movie>>()

    init {
        loadMovies()
    }

    private fun loadMovies() {
        val movieDisposable = movieRepository
                .getMovies()
                .observeOn(schedulers.ui)
                .subscribe(this::onSuccess, this::onError)

        disposeWhenClear(movieDisposable)
    }

    private fun onSuccess(result: List<Movie>) {
        liveMovieList.value = result
    }

    private fun onError(t: Throwable) {
        Timber.e(t)
    }
}