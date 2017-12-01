package crepe.dan.moovie.dashboard

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.moviesource.MovieRepository
import com.example.moviesource.entities.Movie
import crepe.dan.moovie.R
import crepe.dan.moovie.dashboard.list.MovieAdapter
import dagger.android.support.DaggerFragment
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.fragment_dashboard.*
import javax.inject.Inject

class DashboardFragment : DaggerFragment() {

    private val movieList = ArrayList<Movie>()
    private val adapter = MovieAdapter(movieList)
    private var getMovies:Disposable? = null

    @Inject
    lateinit var movieRepo : MovieRepository

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvMovies.adapter = adapter
    }

    override fun onStart() {
        super.onStart()
        Toast.makeText(context, "Started loading", Toast.LENGTH_SHORT).show()
        getMovies = movieRepo.getMovies()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {results ->
                            onMoviesFetched(results)
                        },
                        {error ->
                            Log.e("WOW", error.message)
                            Toast.makeText(context, "Error:  " + error.localizedMessage, Toast.LENGTH_SHORT).show()
                        }
                )
    }

    override fun onStop() {
        getMovies?.dispose()
        super.onStop()
    }

    private fun onMoviesFetched(movies: List<Movie>) {
        movieList.clear()
        movieList.addAll(movies)
        adapter.notifyDataSetChanged()
    }

}