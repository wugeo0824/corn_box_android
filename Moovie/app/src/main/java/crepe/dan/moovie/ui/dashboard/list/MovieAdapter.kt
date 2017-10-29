package crepe.dan.moovie.ui.dashboard.list

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import crepe.dan.moovie.R
import crepe.dan.moovie.data.source.MovieLocalCache
import crepe.dan.moovie.data.source.MovieRemoteSource
import crepe.dan.moovie.data.MovieRepository
import crepe.dan.moovie.model.Movie

class MovieAdapter: RecyclerView.Adapter<MovieViewHolder>() {

    private val cache: MovieLocalCache = MovieLocalCache()
    private val remote: MovieRemoteSource = MovieRemoteSource()

    private val movieRepo:MovieRepository = MovieRepository(cache, remote)

    fun refreshData(movieList: List<Movie>) {

    }

    override fun getItemCount(): Int {
        return 10
    }

    override fun onBindViewHolder(holder: MovieViewHolder?, position: Int) {

    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent!!.context).inflate(R.layout.item_movie, parent, false)
        return MovieViewHolder(view)
    }
}