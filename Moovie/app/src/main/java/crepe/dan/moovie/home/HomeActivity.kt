package crepe.dan.moovie.home

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import crepe.dan.moovie.R
import crepe.dan.moovie.extensions.replaceFragment
import crepe.dan.moovie.home.dashboard.DashboardJFragment
import crepe.dan.moovie.home.movies.MoviesFragment
import crepe.dan.moovie.home.profile.ProfileFragment
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

enum class HomeFragments constructor(val tag: String) {
    DASHBOARD("dashboard_fragment"),
    MOVIES("movies_fragment"),
    PROFILE("profile_fragment")
}

class HomeActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        if (savedInstanceState == null) {
            switchToFragment(HomeFragments.DASHBOARD)
        }
    }

    override fun onBackPressed() {
        finish()
        //super.onBackPressed()
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_dashboard -> {
                switchToFragment(HomeFragments.DASHBOARD)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_movies -> {
                switchToFragment(HomeFragments.MOVIES)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_profile -> {
                switchToFragment(HomeFragments.PROFILE)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    private fun switchToFragment(nextFragment: HomeFragments) {
        var fragmentToBeReplaced = supportFragmentManager.findFragmentByTag(nextFragment.tag)

        if (fragmentToBeReplaced == null) {
            fragmentToBeReplaced = when (nextFragment) {
                HomeFragments.DASHBOARD -> {
                    DashboardJFragment()
                }
                HomeFragments.MOVIES -> {
                    MoviesFragment()
                }
                HomeFragments.PROFILE -> {
                    ProfileFragment()
                }
            }
        }

        replaceFragment(fragmentToBeReplaced, R.id.flFrame, nextFragment.tag)
    }
}
