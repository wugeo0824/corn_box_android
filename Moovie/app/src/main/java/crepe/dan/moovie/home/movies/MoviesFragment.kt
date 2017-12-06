package crepe.dan.moovie.home.movies

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.moviesource.entities.Movie
import crepe.dan.moovie.R
import crepe.dan.moovie.extensions.observeK
import crepe.dan.moovie.home.movies.list.MovieAdapter
import crepe.dan.moovie.view.ViewState
import crepe.dan.moovie.view.ViewStateResource
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

        viewModel.moviesLiveData.observeK(this, this::onMoviesFetched)
        viewModel.viewStateLiveData.observeK(this, this::onViewStateChanged)

        swipeRefreshLayout.setOnRefreshListener(viewModel::loadMovies)
    }

    private fun onMoviesFetched(movies: List<Movie>?) {
        movies?.let {
            adapter.setItems(movies)
        }
    }

    private fun onViewStateChanged(viewStateResource: ViewStateResource?) {
        when (viewStateResource?.state) {
            ViewState.LOADING -> {
                swipeRefreshLayout.isRefreshing = true
            }
            ViewState.SUCCESS -> {
                swipeRefreshLayout.isRefreshing = false
            }
            ViewState.ERROR -> {
                swipeRefreshLayout.isRefreshing = false
                Snackbar.make(coordinatorLayout, viewStateResource.message ?: getString(R.string.message_unknown_error), Snackbar.LENGTH_SHORT).show()
            }
            ViewState.EMPTY -> {
                swipeRefreshLayout.isRefreshing = false
                Snackbar.make(coordinatorLayout, getString(R.string.message_empty_content), Snackbar.LENGTH_LONG)
                        .setAction(R.string.button_text_dismiss, { }).show()
            }
        }
    }
}