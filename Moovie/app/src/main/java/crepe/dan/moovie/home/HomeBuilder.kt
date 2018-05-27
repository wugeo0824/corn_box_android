package crepe.dan.moovie.home

import crepe.dan.moovie.home.dashboard.DashboardBuilder
import crepe.dan.moovie.home.movies.MoviesBuilder
import crepe.dan.moovie.home.profile.ProfileBuilder
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by xijun on 30/11/2017.
 */

@Module
internal abstract class HomeBuilder {

    @ContributesAndroidInjector(modules = [
        DashboardBuilder::class,
        MoviesBuilder::class,
        ProfileBuilder::class
    ])
    internal abstract fun homeActivity(): HomeActivity

}
