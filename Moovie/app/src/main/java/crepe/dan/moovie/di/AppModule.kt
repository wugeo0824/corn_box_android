package crepe.dan.moovie.di

import android.app.Application
import android.content.Context
import com.example.base.RxSchedulers
import crepe.dan.moovie.MoovieApplication
import dagger.Module
import dagger.Provides
import java.io.File
import javax.inject.Named
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun provideContext(application: Application): Context {
        return application.applicationContext
    }

    @Provides
    @Singleton
    @Named("cache")
    fun provideCacheDir(application: MoovieApplication): File {
        return application.cacheDir
    }

    @Singleton
    @Provides
    fun provideRxSchedulers(): RxSchedulers {
        return RxSchedulers()
    }
}