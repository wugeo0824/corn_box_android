package crepe.dan.moovie.home

import dagger.Subcomponent
import dagger.android.AndroidInjector

@Subcomponent
//(modules = arrayOf(MainActivityModule::class))
interface MainActivityComponent : AndroidInjector<MainActivity> {

    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<MainActivity>()

}