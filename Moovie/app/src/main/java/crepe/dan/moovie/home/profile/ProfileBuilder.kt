package crepe.dan.moovie.home.profile

import android.arch.lifecycle.ViewModel
import crepe.dan.moovie.di.ViewModelKey
import crepe.dan.moovie.home.movies.MoviesViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

/**
 * Created by xijun on 01/12/2017.
 */
@Module
internal abstract class ProfileBuilder {

    @ContributesAndroidInjector
    internal abstract fun profileFragment(): ProfileFragment

    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel::class)
    abstract fun bindsProfileViewModel(viewModel: ProfileViewModel): ViewModel
}
