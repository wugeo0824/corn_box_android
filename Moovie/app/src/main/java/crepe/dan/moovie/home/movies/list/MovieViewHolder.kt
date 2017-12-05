package crepe.dan.moovie.home.movies.list

import android.support.v7.widget.RecyclerView
import android.view.View
import com.example.moviesource.entities.Movie
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

    fun setItem(movie: Movie) {
        itemView.tvName.text = movie.name
        itemView.tvDescription.text = movie.description
    }
}
