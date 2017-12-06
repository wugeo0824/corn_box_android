package crepe.dan.moovie.extensions

import android.support.annotation.LayoutRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.loadImageFromUrl(url: String) {
    Glide.with(this).load(url).into(this)
}

fun inflateView(parent: ViewGroup, @LayoutRes layoutResId: Int, attachToRoot: Boolean): View {
    return LayoutInflater.from(parent.context).inflate(layoutResId, parent, attachToRoot)
}