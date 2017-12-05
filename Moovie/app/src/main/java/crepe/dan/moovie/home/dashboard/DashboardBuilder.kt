package crepe.dan.moovie.home.dashboard

import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by xijun on 01/12/2017.
 */
@Module
internal abstract class DashboardBuilder {

    @ContributesAndroidInjector
    internal abstract fun cinemasFragment(): DashboardFragment
}