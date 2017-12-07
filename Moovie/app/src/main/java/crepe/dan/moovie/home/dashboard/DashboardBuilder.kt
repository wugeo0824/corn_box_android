package crepe.dan.moovie.home.dashboard

import android.arch.lifecycle.ViewModel
import crepe.dan.moovie.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

/**
 * Created by xijun on 01/12/2017.
 */
@Module
internal abstract class DashboardBuilder {

    @ContributesAndroidInjector
    internal abstract fun cinemasFragment(): DashboardFragment

    @Binds
    @IntoMap
    @ViewModelKey(DashboardViewModel::class)
    abstract fun bindDashboardViewModel(viewModel: DashboardViewModel): ViewModel
}