package crepe.dan.moovie.splash

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import crepe.dan.moovie.home.HomeActivity

class SplashActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startMainActivity()
    }

    private fun startMainActivity() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }
}