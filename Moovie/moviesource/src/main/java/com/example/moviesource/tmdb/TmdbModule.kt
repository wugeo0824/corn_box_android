package com.example.moviesource.tmdb

import com.example.moviesource.BuildConfig
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
 * Created by xijun on 27/11/2017.
 */

private const val MAX_CACHE_SIZE_IN_MB: Long = 10 * 1024 * 1024 //10mb

@Module
class TmdbModule {

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
}