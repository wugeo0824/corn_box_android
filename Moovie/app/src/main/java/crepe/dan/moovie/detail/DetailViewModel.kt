package crepe.dan.moovie.detail

import com.example.moviesource.BookmarkRepository
import com.example.moviesource.entities.Movie
import crepe.dan.moovie.utils.RxAwareViewModel
import javax.inject.Inject

class DetailViewModel @Inject constructor(
        private val bookmarkRepository: BookmarkRepository
): RxAwareViewModel() {

    fun start(movie: Movie) {

    }
}