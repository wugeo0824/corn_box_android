package crepe.dan.moovie.home.dashboard

import android.arch.lifecycle.MutableLiveData
import com.example.moviesource.MovieRepository
import com.example.moviesource.entities.Movie
import crepe.dan.moovie.utils.RxAwareViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import timber.log.Timber
import javax.inject.Inject

class DashboardViewModel @Inject constructor(
        private val movieRepo: MovieRepository
) : RxAwareViewModel() {

    val movieLiveData = MutableLiveData<List<Movie>>()

    init {
        discoverMovies()
    }

    fun popFirstMovie() {
        //TODO: Refactor this
        var tempList = movieLiveData.value
        tempList = tempList?.drop(1)
        movieLiveData.value = tempList
    }

    private fun discoverMovies() {
        val discoverDisposable = movieRepo
                .dicoverMovies()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onSuccess, this::onError)

        disposeWhenClear(discoverDisposable)
    }

    private fun onSuccess(movieList: List<Movie>) {
        movieLiveData.value = movieList
    }

    private fun onError(t: Throwable) {

    }
}
