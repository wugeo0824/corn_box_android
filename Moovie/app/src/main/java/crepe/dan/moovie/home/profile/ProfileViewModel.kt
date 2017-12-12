package crepe.dan.moovie.home.profile

import android.arch.lifecycle.MutableLiveData
import com.example.base.RxSchedulers
import com.example.moviesource.BookmarkRepository
import com.example.moviesource.MovieRepository
import com.example.moviesource.daos.BookmarkedDao
import com.example.moviesource.entities.Movie
import crepe.dan.moovie.utils.RxAwareViewModel
import crepe.dan.moovie.view.ViewState
import crepe.dan.moovie.view.ViewStateResource
import timber.log.Timber
import javax.inject.Inject

class ProfileViewModel @Inject constructor(
        private val schedulers: RxSchedulers,
        private val bookmarkRepo: BookmarkRepository
) : RxAwareViewModel() {

    val bookmarkLiveData = MutableLiveData<List<Movie>>()
    val viewStateLiveData = MutableLiveData<ViewStateResource>()

    init {
        loadBookmarks()
    }

    fun loadBookmarks() {
        val movieDisposable = bookmarkRepo
                .getBookmarks()
                .subscribeOn(schedulers.data)
                .doOnSubscribe({ viewStateLiveData.value = ViewStateResource(ViewState.LOADING) })
                .observeOn(schedulers.ui)
                .subscribe(this::onSuccess, this::onError)

        disposeWhenClear(movieDisposable)
    }

    private fun onSuccess(result: List<Movie>) {
        viewStateLiveData.value =
                ViewStateResource(if (result.isEmpty()) ViewState.EMPTY else ViewState.SUCCESS)

        bookmarkLiveData.value = result
    }

    private fun onError(t: Throwable) {
        Timber.e(t)
        viewStateLiveData.value = ViewStateResource(ViewState.ERROR, t.localizedMessage)
    }

}
