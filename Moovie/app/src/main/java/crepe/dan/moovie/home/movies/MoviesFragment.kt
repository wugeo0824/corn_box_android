package crepe.dan.moovie.home.movies

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import crepe.dan.moovie.R
import crepe.dan.moovie.home.movies.list.MovieAdapter
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_movies.*
import javax.inject.Inject

class MoviesFragment : DaggerFragment() {

    private val adapter = MovieAdapter()

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: MoviesViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_movies, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvMovies.adapter = adapter

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MoviesViewModel::class.java)

        viewModel.liveMovieList.observe(this, Observer { result ->
            result?.let {
                adapter.setItems(result)
            }
        })
    }
}