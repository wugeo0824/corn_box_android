package crepe.dan.moovie.home

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import crepe.dan.moovie.R
import crepe.dan.moovie.home.cinemas.CinemasFragment
import crepe.dan.moovie.utils.replaceFragment
import crepe.dan.moovie.home.movies.MoviesFragment
import crepe.dan.moovie.home.profile.ProfileFragment
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class HomeActivity : DaggerAppCompatActivity() {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_cinemas -> {
                replaceContainerWith(CinemasFragment())
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_movies -> {
                replaceContainerWith(MoviesFragment())
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_profile -> {
                replaceContainerWith(ProfileFragment())
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        if (savedInstanceState == null){
            replaceContainerWith(CinemasFragment())
        }
    }

    private fun replaceContainerWith(fragment: Fragment) {
        replaceFragment(fragment, R.id.flFrame)
    }
}
