package crepe.dan.moovie.extensions

import android.support.annotation.LayoutRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import crepe.dan.moovie.R
import crepe.dan.moovie.di.GlideApp

fun ImageView.loadImageFromUrlWithFitCenter(url: String) {
    GlideApp.with(this)
            .load(url)
            .fitCenter()
            .into(this)
}

fun ImageView.loadThumbnailFromUrl(url: String) {
    GlideApp.with(this)
            .load(url)
            .centerCrop()
            .placeholder(R.drawable.img_movie_poster_placeholder)
            .into(this)
}

fun inflateView(parent: ViewGroup, @LayoutRes layoutResId: Int, attachToRoot: Boolean): View {
    return LayoutInflater.from(parent.context).inflate(layoutResId, parent, attachToRoot)
}