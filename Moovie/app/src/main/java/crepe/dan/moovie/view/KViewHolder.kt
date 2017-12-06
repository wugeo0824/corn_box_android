package crepe.dan.moovie.view

import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import crepe.dan.moovie.extensions.inflateView

open class KViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    constructor(parent: ViewGroup, @LayoutRes layoutResId: Int) : this(inflateView(parent, layoutResId, false))
}