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
import com.mindorks.placeholderview.listeners.ItemRemovedListener;

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
        swipePlaceHolderView.getBuilder()
                .setDisplayViewCount(3)
                .setHeightSwipeDistFactor(8)
                .setWidthSwipeDistFactor(4)
                .setSwipeDecor(new SwipeDecor()
                        .setPaddingTop(-30)
                        .setRelativeScale(0.01f)
                        .setSwipeInMsgLayoutId(R.layout.item_swipe_in_message_view)
                        .setSwipeOutMsgLayoutId(R.layout.item_swipe_out_message_view)
                        .setSwipeMaxChangeAngle(1f));
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        swipePlaceHolderView.addItemRemoveListener(count -> dashboardViewModel.popFirstMovie());

        dashboardViewModel.getMovieLiveData().observe(this, this::displayMovies);
    }

    private void displayMovies(@Nullable List<Movie> movies) {
        int currentSize = swipePlaceHolderView.getAllResolvers().size();

        if (movies != null && currentSize != movies.size()) {

            swipePlaceHolderView.removeAllViews();

            for (Movie item : movies) {
                SwipeMovieCardView swipeMovieCardView = new SwipeMovieCardView(item, dashboardViewModel);
                swipePlaceHolderView.addView(swipeMovieCardView);
            }
        }
    }
}
