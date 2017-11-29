package crepe.dan.moovie.dashboard.list

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import crepe.dan.moovie.R

class MovieAdapter : RecyclerView.Adapter<MovieViewHolder>() {

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