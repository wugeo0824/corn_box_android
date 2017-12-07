package crepe.dan.moovie.extensions

import android.support.annotation.LayoutRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import crepe.dan.moovie.R
import crepe.dan.moovie.di.GlideApp

fun ImageView.loadImageFromUrl(url: String) {
    Glide.with(this)
            .load(url)
            .into(this)
}

fun ImageView.loadImageFromUrlWithFitCenter(url: String) {
    GlideApp.with(this)
            .load(url)
            .fitCenter()
            .into(this)
}

fun ImageView.loadThumbnailFromUrl(url: String) {
    GlideApp.with(this.context)
            .asDrawable()
            .diskCacheStrategy(DiskCacheStrategy.DATA)
            .override(140, 210)
            .transition(withCrossFade())
            .placeholder(R.drawable.img_movie_poster_placeholder)
            .load(url)
            .into(this)
}

fun inflateView(parent: ViewGroup, @LayoutRes layoutResId: Int, attachToRoot: Boolean): View {
    return LayoutInflater.from(parent.context).inflate(layoutResId, parent, attachToRoot)
}