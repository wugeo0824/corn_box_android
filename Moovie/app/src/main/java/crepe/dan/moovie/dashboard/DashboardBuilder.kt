package crepe.dan.moovie.dashboard

import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by xijun on 30/11/2017.
 */

@Module
internal abstract class DashboardBuilder {

    @ContributesAndroidInjector
    internal abstract fun dashboardFragment(): DashboardFragment
}