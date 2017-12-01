package crepe.dan.moovie.home.movies

import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by xijun on 30/11/2017.
 */

@Module
internal abstract class MoviesBuilder {

    @ContributesAndroidInjector
    internal abstract fun moviesFragment(): MoviesFragment
}