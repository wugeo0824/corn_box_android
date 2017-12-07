package crepe.dan.moovie.home.dashboard;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.moviesource.entities.Movie;
import com.mindorks.placeholderview.SwipeDecor;
import com.mindorks.placeholderview.SwipePlaceHolderView;

import java.util.List;

import javax.inject.Inject;

import crepe.dan.moovie.R;
import dagger.android.support.DaggerFragment;

public class DashboardJFragment extends DaggerFragment {

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    private DashboardViewModel dashboardViewModel;

    private SwipePlaceHolderView swipePlaceHolderView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dashboardViewModel = ViewModelProviders.of(this, viewModelFactory).get(DashboardViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        swipePlaceHolderView = view.findViewById(R.id.galleryView);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        swipePlaceHolderView.getBuilder()
                .setDisplayViewCount(3)
                .setSwipeDecor(new SwipeDecor()
                        .setPaddingTop(-20)
                        .setRelativeScale(0.01f)
                        .setSwipeInMsgLayoutId(R.layout.item_swipe_in_message_view)
                        .setSwipeOutMsgLayoutId(R.layout.item_swipe_out_message_view));

        dashboardViewModel.getMovieLiveData().observe(this, this::onMoviesFetched);
    }

    private void onMoviesFetched(@Nullable List<Movie> movies) {
        if (movies != null) {
            for (Movie item : movies) {
                SwipeMovieCardView swipeMovieCardView = new SwipeMovieCardView(item);
                swipePlaceHolderView.addView(swipeMovieCardView);
            }
        }
    }
}
