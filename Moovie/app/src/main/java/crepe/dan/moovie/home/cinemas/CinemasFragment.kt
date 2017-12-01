package crepe.dan.moovie.home.cinemas

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import crepe.dan.moovie.R
import dagger.android.support.DaggerFragment

/**
 * Created by xijun on 01/12/2017.
 */

class CinemasFragment: DaggerFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_cinemas, container, false)
    }
}