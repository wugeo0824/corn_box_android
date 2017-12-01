package crepe.dan.moovie.home.cinemas

import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by xijun on 01/12/2017.
 */
@Module
internal abstract class CinemasBuilder {

    @ContributesAndroidInjector
    internal abstract fun cinemasFragment(): CinemasFragment
}