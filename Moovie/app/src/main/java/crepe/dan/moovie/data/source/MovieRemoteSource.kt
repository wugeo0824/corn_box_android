package crepe.dan.moovie.data.source

import crepe.dan.moovie.model.Movie
import io.reactivex.Single

class MovieRemoteSource: MovieDataSource {

    override fun getMovie(movieID: String): Single<Movie> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getMovies(): Single<List<Movie>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}