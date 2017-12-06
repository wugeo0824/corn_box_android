package crepe.dan.moovie.extensions

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer

/**
 * Kotlin powered observe function
 */

inline fun <T> LiveData<T>.observeK(lifecycleOwner: LifecycleOwner, crossinline observer: (T?) -> Unit) {
    observe(lifecycleOwner, Observer { observer(it) })
}