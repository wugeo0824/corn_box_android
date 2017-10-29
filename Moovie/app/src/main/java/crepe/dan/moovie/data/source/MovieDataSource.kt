package crepe.dan.moovie.data.source

import crepe.dan.moovie.model.Movie
import io.reactivex.Single

interface MovieDataSource {

    fun getMovie(movieID: String): Single<Movie>

    fun getMovies(): Single<List<Movie>>

}