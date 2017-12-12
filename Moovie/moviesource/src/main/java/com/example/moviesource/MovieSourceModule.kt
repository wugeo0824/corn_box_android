package com.example.moviesource

import android.arch.persistence.room.Room
import android.content.Context
import android.os.Debug
import com.example.moviesource.daos.BookmarkedDao
import com.example.moviesource.daos.MovieDao
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

private const val MAX_CACHE_SIZE_IN_MB: Long = 10 * 1024 * 1024 //10mb

@Module
class MovieSourceModule {

    @Singleton
    @Provides
    fun provideDatabase(context: Context): MovieDatabase {
        val builder = Room.databaseBuilder(context, MovieDatabase::class.java, "movies_database.db")
                .fallbackToDestructiveMigration()
        if (Debug.isDebuggerConnected()) {
            builder.allowMainThreadQueries()
        }
        return builder.build()
    }

    @Provides
    fun provideMovieDao(movieDatabase: MovieDatabase): MovieDao = movieDatabase.movieDao()

    @Provides
    fun provideBookmarkedDao(movieDatabase: MovieDatabase): BookmarkedDao = movieDatabase.bookmarkedDao()

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
    fun provideLocalCache(movieDao: MovieDao): MovieLocalCache = MovieLocalCache(movieDao)

    @Singleton
    @Provides
    fun provideRemoveSource(movieFetcher: TmdbMovieFetcher): MovieRemoteSource = MovieRemoteSource(movieFetcher)

    @Singleton
    @Provides
    fun provideMovieRepository(local: MovieLocalCache, remote: MovieRemoteSource): MovieRepository {
        return MovieRepository(local, remote)
    }
}
