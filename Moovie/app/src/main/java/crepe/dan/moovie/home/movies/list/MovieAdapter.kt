package crepe.dan.moovie.home.movies.list

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.example.moviesource.entities.Movie
import crepe.dan.moovie.detail.DetailActivity

class MovieAdapter : RecyclerView.Adapter<MovieViewHolder>() {

    private val items = ArrayList<Movie>()

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val item = items[position]
        val onClickListener = View.OnClickListener { DetailActivity.startActivity(holder.itemView.context, item) }

        holder.setItem(item)
        holder.setOnClickListener(onClickListener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(parent)
    }

    fun setItems(movies: List<Movie>) {
        items.clear()
        items.addAll(movies)
        notifyDataSetChanged()
    }
}