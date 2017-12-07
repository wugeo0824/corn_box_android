package crepe.dan.moovie.home.dashboard

import android.arch.lifecycle.MutableLiveData
import crepe.dan.moovie.utils.RxAwareViewModel
import java.util.*
import javax.inject.Inject

class DashboardViewModel @Inject constructor():RxAwareViewModel() {

    var numberLiveData = MutableLiveData<Int>()

    private val random = Random()

    fun nextRandom() {
        numberLiveData.value = random.nextInt(100)
    }

}