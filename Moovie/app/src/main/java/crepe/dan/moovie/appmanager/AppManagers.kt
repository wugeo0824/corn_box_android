package crepe.dan.moovie.appmanager

import android.app.Application
import crepe.dan.moovie.BuildConfig
import timber.log.Timber
import javax.inject.Inject

class AppManagers(private vararg val managers: AppManager) : AppManager {

    override fun init(application: Application) {
        managers.forEach {
            it.init(application)
        }
    }
}

class TimberManager @Inject constructor() : AppManager {
    
    override fun init(application: Application) {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}