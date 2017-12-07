package crepe.dan.moovie.home.dashboard

import android.widget.ImageView
import android.widget.TextView
import com.example.moviesource.entities.Movie
import com.mindorks.placeholderview.annotations.Layout
import com.mindorks.placeholderview.annotations.Resolve
import com.mindorks.placeholderview.annotations.View
import crepe.dan.moovie.R
import crepe.dan.moovie.extensions.loadImageFromUrl
import com.mindorks.placeholderview.annotations.swipe.SwipeOutState
import com.mindorks.placeholderview.annotations.swipe.SwipeInState
import com.mindorks.placeholderview.annotations.swipe.SwipeIn
import com.mindorks.placeholderview.annotations.swipe.SwipeCancelState
import com.mindorks.placeholderview.annotations.swipe.SwipeOut
import timber.log.Timber


@Layout(R.layout.item_movie_swipe_card)
class SwipeMovieCardView constructor(
        private val movie: Movie
) {

    @View(R.id.ivCardPoster)
    private lateinit var ivCardPoster: ImageView

    @View(R.id.tvCardName)
    private lateinit var tvCardName: TextView

    @View(R.id.tvCardScore)
    private lateinit var tvCardScore: TextView

    @Resolve
    private fun onResolved() {
        ivCardPoster.loadImageFromUrl(movie.posterUrl)
        tvCardName.text = movie.name
        tvCardScore.text = "Score: ${movie.averageVote}"
    }

    @SwipeOut
    private fun onSwipedOut() {
        Timber.d("onSwipedOut")
    }

    @SwipeCancelState
    private fun onSwipeCancelState() {
        Timber.d("onSwipeCancelState")
    }

    @SwipeIn
    private fun onSwipeIn() {
        Timber.d("onSwipedIn")
    }

    @SwipeInState
    private fun onSwipeInState() {
        Timber.d("onSwipeInState")
    }

    @SwipeOutState
    private fun onSwipeOutState() {
        Timber.d("onSwipeOutState")
    }
}
