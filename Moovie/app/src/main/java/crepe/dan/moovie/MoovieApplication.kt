package crepe.dan.moovie

import crepe.dan.moovie.appmanager.AppManagers
import crepe.dan.moovie.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import javax.inject.Inject

class MoovieApplication : DaggerApplication() {

    @Inject lateinit var managers: AppManagers

    override fun onCreate() {
        super.onCreate()
        managers.init(this)
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().create(this)
    }
}