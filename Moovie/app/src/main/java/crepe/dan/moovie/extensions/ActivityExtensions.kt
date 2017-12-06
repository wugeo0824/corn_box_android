package crepe.dan.moovie.extensions

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity


fun AppCompatActivity.addFragment(fragment: Fragment, frameId: Int, tag: String) {
    supportFragmentManager.transact {
        add(frameId, fragment, tag)
        setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
    }
}


fun AppCompatActivity.replaceFragment(fragment: Fragment, frameId: Int, tag: String) {
    supportFragmentManager.transact {
        replace(frameId, fragment, tag)
        setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
    }
}

/**
 * Runs a FragmentTransaction, then calls commit().
 */
private inline fun FragmentManager.transact(action: FragmentTransaction.() -> Unit) {
    beginTransaction()
            .setReorderingAllowed(true)
            .apply {
                action()
            }
            .commit()
}