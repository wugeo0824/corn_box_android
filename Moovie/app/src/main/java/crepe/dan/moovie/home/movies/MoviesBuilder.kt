package crepe.dan.moovie.home.movies

import android.arch.lifecycle.ViewModel
import crepe.dan.moovie.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

/**
 * Created by xijun on 30/11/2017.
 */

@Module
internal abstract class MoviesBuilder {

    @ContributesAndroidInjector
    internal abstract fun moviesFragment(): MoviesFragment

    @Binds
    @IntoMap
    @ViewModelKey(MoviesViewModel::class)
    abstract fun bindMoviesViewModel(viewModel: MoviesViewModel): ViewModel
}