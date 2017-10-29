package crepe.dan.moovie.data

import crepe.dan.moovie.data.source.MovieLocalCache
import crepe.dan.moovie.data.source.MovieRemoteSource
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MovieDataModule {

    @Singleton
    @Provides
    fun provideMovieRemoteSource(): MovieRemoteSource = MovieRemoteSource()

    @Singleton
    @Provides
    fun provideMovieLocalCache(): MovieLocalCache = MovieLocalCache()

    @Singleton
    @Provides
    fun provideMovieRepository(localCache: MovieLocalCache, remoteSource: MovieRemoteSource): MovieRepository {
        return MovieRepository(localCache, remoteSource)
    }
}