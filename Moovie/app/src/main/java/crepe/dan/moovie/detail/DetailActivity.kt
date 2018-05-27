package crepe.dan.moovie.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.moviesource.entities.Movie
import crepe.dan.moovie.R
import crepe.dan.moovie.extensions.loadImageFromUrl
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : DaggerAppCompatActivity() {

    companion object {
        private const val DATA_MOVIE_KEY = "INTENT_DATA_MOVIE"

        fun startActivity(context: Context, movie: Movie) {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(DATA_MOVIE_KEY, movie)

            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_detail)
    }

    override fun onStart() {
        super.onStart()

        val movie: Movie = intent.extras.get("INTENT_DATA_MOVIE") as Movie

        ivDetailPoster.loadImageFromUrl(movie.posterUrl)
        tvDetailTitle.text = movie.title
    }
}