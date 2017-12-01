package crepe.dan.moovie.home.profile

import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by xijun on 01/12/2017.
 */
@Module
internal abstract class ProfileBuilder {

    @ContributesAndroidInjector
    internal abstract fun profileFragment(): ProfileFragment
}