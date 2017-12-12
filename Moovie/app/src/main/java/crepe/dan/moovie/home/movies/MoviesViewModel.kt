package crepe.dan.moovie.home.movies

import android.arch.lifecycle.MutableLiveData
import com.example.base.RxSchedulers
import com.example.moviesource.MovieRepository
import com.example.moviesource.entities.Movie
import crepe.dan.moovie.utils.RxAwareViewModel
import crepe.dan.moovie.view.ViewState
import crepe.dan.moovie.view.ViewStateResource
import timber.log.Timber
import javax.inject.Inject

class MoviesViewModel @Inject constructor(
        private val schedulers: RxSchedulers,
        private val movieRepository: MovieRepository
) : RxAwareViewModel() {

    val moviesLiveData = MutableLiveData<List<Movie>>()
    val viewStateLiveData = MutableLiveData<ViewStateResource>()

    init {
        loadMovies()
    }

    fun loadMovies() {
        val movieDisposable = movieRepository
                .getMovies()
                .subscribeOn(schedulers.data)
                .doOnSubscribe({ viewStateLiveData.value = ViewStateResource(ViewState.LOADING) })
                .observeOn(schedulers.ui)
                .subscribe(this::onSuccess, this::onError)

        disposeWhenClear(movieDisposable)
    }

    private fun onSuccess(result: List<Movie>) {
        viewStateLiveData.value =
                ViewStateResource(if (result.isEmpty()) ViewState.EMPTY else ViewState.SUCCESS)

        moviesLiveData.value = result
    }

    private fun onError(t: Throwable) {
        Timber.e(t)
        viewStateLiveData.value = ViewStateResource(ViewState.ERROR, t.localizedMessage)
    }

}
