package crepe.dan.moovie.ui.dashboard.list

import android.support.v7.widget.RecyclerView
import android.view.View
import crepe.dan.moovie.model.Movie
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

    fun setItem(movie:Movie) {
        itemView.tvName.text = movie.name
        itemView.tvDescription.text = movie.description
    }
}
