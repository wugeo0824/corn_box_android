package crepe.dan.moovie.home

import crepe.dan.moovie.dashboard.DashboardBuilder
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by xijun on 30/11/2017.
 */

@Module
internal abstract class HomeBuilder {

    @ContributesAndroidInjector(modules = [
        HomeModule::class,
        DashboardBuilder::class])
    internal abstract fun homeActivity(): HomeActivity
}