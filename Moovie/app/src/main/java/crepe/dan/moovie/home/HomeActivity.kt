package crepe.dan.moovie.home

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import crepe.dan.moovie.R
import crepe.dan.moovie.home.dashboard.DashboardFragment
import crepe.dan.moovie.home.movies.MoviesFragment
import crepe.dan.moovie.home.profile.ProfileFragment
import crepe.dan.moovie.extensions.replaceFragment
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class HomeActivity : DaggerAppCompatActivity() {

    enum class HomeFragments constructor(val tag: String) {
        DASHBOARD("dashboard_fragment"),
        MOVIES("movies_fragment"),
        PROFILE("profile_fragment")
    }

    private val fragmentHashMap = HashMap<String, Fragment>(3)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fragmentHashMap.put(HomeFragments.DASHBOARD.tag, DashboardFragment())
        fragmentHashMap.put(HomeFragments.MOVIES.tag, MoviesFragment())
        fragmentHashMap.put(HomeFragments.PROFILE.tag, ProfileFragment())

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        if (savedInstanceState == null) {
            switchToFragment(HomeFragments.DASHBOARD)
        }
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
        var fragmentToBeReplaced = fragmentHashMap[nextFragment.tag]

        if (fragmentToBeReplaced == null) {
            fragmentToBeReplaced = when (nextFragment) {
                HomeFragments.DASHBOARD -> {
                    DashboardFragment()
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
