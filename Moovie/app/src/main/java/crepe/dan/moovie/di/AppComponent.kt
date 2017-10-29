package crepe.dan.moovie.di

import android.app.Application
import crepe.dan.moovie.MoovieApplication
import crepe.dan.moovie.data.MovieDataModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@Component(modules = arrayOf(
        AndroidSupportInjectionModule::class,
        AppModule::class,
        ActivityBuilder::class,
        MovieDataModule::class
))
interface AppComponent : AndroidInjector<MoovieApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    override fun inject(app: MoovieApplication)
}