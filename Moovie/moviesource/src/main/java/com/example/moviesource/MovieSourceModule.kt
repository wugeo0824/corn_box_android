package com.example.moviesource

import com.example.moviesource.source.MovieLocalCache
import com.example.moviesource.source.MovieRemoteSource
import com.example.moviesource.tmdb.TmdbMovieFetcher
import com.uwetrottmann.tmdb2.Tmdb
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.io.File
import javax.inject.Named
import javax.inject.Singleton

/**
 * Created by xijun on 21/11/2017.
 */

private const val MAX_CACHE_SIZE_IN_MB: Long = 10 * 1024 * 1024 //10mb

@Module
class MovieSourceModule {

    @Singleton
    @Provides
    fun provideTmdb(@Named("cache") cacheDir: File, interceptor: HttpLoggingInterceptor): Tmdb {
        return object : Tmdb(BuildConfig.THE_MOVIE_DATABASE_API_KEY) {
            override fun setOkHttpClientDefaults(builder: OkHttpClient.Builder) {
                super.setOkHttpClientDefaults(builder)
                builder.apply {
                    addInterceptor(interceptor)
                    cache(Cache(File(cacheDir, "tmdb_cache"), MAX_CACHE_SIZE_IN_MB))
                }
            }
        }
    }

    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor():HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            if (BuildConfig.DEBUG)
                level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Singleton
    @Provides
    fun provideLocalCache(): MovieLocalCache = MovieLocalCache()

    @Singleton
    @Provides
    fun provideRemoveSource(movieFetcher: TmdbMovieFetcher): MovieRemoteSource = MovieRemoteSource(movieFetcher)

    @Singleton
    @Provides
    fun provideMovieRepository(local: MovieLocalCache, remote: MovieRemoteSource): MovieRepository {
        return MovieRepository(local, remote)
    }
}