package crepe.dan.moovie.detail

import android.arch.lifecycle.ViewModel
import crepe.dan.moovie.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
internal abstract class DetailBuilder {
    @ContributesAndroidInjector
    internal abstract fun detailActivity(): DetailActivity

    @Binds
    @IntoMap
    @ViewModelKey(DetailViewModel::class)
    abstract fun bindDetailViewModel(viewModel: DetailViewModel): ViewModel
}