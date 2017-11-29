package crepe.dan.moovie.di

import android.app.Application
import crepe.dan.moovie.MoovieApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@Component(modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class,
        ActivityBuilder::class
        //MovieSourceModule::class
])
interface AppComponent : AndroidInjector<MoovieApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    override fun inject(app: MoovieApplication)
}