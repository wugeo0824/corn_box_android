package crepe.dan.moovie.data

import crepe.dan.moovie.data.source.MovieDataSource
import crepe.dan.moovie.data.source.MovieLocalCache
import crepe.dan.moovie.data.source.MovieRemoteSource
import crepe.dan.moovie.model.Movie
import io.reactivex.Single

/**
 * Created by xijunli on 20/10/17.
 */
class MovieRepository(private var cache: MovieLocalCache, private var remote: MovieRemoteSource) : MovieDataSource {

    override fun getMovie(movieID: String): Single<Movie> {
        return cache.getMovie(movieID).onErrorResumeNext { remote.getMovie(movieID) }
    }

    override fun getMovies(): Single<List<Movie>> {
       return Single.concat(cache.getMovies(), remote.getMovies()).firstOrError()
    }

}