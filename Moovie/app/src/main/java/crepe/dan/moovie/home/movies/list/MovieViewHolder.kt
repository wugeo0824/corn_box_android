package crepe.dan.moovie.home.movies.list

import android.view.ViewGroup
import com.example.moviesource.entities.Movie
import crepe.dan.moovie.R
import crepe.dan.moovie.extensions.loadImageFromUrl
import crepe.dan.moovie.view.KViewHolder
import kotlinx.android.synthetic.main.item_movie_list.view.*

class MovieViewHolder(parent: ViewGroup) : KViewHolder(parent, R.layout.item_movie_list) {

    fun setItem(movie: Movie) {
        itemView.tvName.text = movie.name
        itemView.tvDescription.text = movie.description
        itemView.ivThumbNail.loadImageFromUrl(movie.posterUrl)
    }
}
