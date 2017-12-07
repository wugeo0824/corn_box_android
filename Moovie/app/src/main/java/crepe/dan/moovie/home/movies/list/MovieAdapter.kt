package crepe.dan.moovie.home.movies.list

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.moviesource.entities.Movie
import crepe.dan.moovie.R

class MovieAdapter: RecyclerView.Adapter<MovieViewHolder>() {

    private val items = ArrayList<Movie>()

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val item = items[position]
        holder.setItem(item)
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