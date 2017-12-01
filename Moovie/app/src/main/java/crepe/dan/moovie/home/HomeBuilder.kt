package crepe.dan.moovie.home

import crepe.dan.moovie.home.cinemas.CinemasBuilder
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
        HomeModule::class,
        CinemasBuilder::class,
        MoviesBuilder::class,
        ProfileBuilder::class
    ])
    internal abstract fun homeActivity(): HomeActivity
}