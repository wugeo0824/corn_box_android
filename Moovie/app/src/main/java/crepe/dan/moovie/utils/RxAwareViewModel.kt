package crepe.dan.moovie.utils

import android.arch.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * Simple ViewModel which exposes a CompositeDisposable which is automatically cleared when
 * the ViewModel is cleared.
 *
 * To prevent memory leak
 */

open class RxAwareViewModel: ViewModel() {

    private val disposables = CompositeDisposable()

    fun disposeWhenClear(disposable: Disposable) {
        disposables.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }
}