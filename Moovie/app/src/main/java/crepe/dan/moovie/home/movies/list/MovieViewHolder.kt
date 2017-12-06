package crepe.dan.moovie.home.movies.list

import android.view.ViewGroup
import com.example.moviesource.entities.Movie
import crepe.dan.moovie.R
import crepe.dan.moovie.view.KViewHolder
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieViewHolder(parent: ViewGroup) : KViewHolder(parent, R.layout.item_movie) {

    fun setItem(movie: Movie) {
        itemView.tvName.text = movie.name
        itemView.tvDescription.text = movie.description
    }
}
