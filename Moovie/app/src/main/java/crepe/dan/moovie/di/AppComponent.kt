package crepe.dan.moovie.di

import com.example.moviesource.MovieSourceModule
import crepe.dan.moovie.MoovieApplication
import crepe.dan.moovie.home.HomeBuilder
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    ViewModelBuilder::class,
    AppModule::class,
    HomeBuilder::class,
    MovieSourceModule::class
])
interface AppComponent : AndroidInjector<MoovieApplication> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<MoovieApplication>()

}
