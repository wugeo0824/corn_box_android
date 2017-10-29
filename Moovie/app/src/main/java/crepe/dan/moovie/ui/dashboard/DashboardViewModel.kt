package crepe.dan.moovie.ui.dashboard

import android.databinding.ObservableField
import crepe.dan.moovie.data.MovieRepository

/**
 * Created by xijunli on 19/10/17.
 */
class DashboardViewModel {

    var isLoading = ObservableField<Boolean>()

//    var movieRepo = MovieRepository()

    fun refresh() {
        isLoading.set(true)

    }
}