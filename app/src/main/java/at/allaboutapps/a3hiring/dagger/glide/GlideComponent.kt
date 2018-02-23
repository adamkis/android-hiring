package at.allaboutapps.a3hiring.dagger.glide

import android.support.v7.widget.RecyclerView
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = arrayOf(
        GlideModule::class))
interface GlideComponent {
    fun inject(viewHolder: RecyclerView.ViewHolder)
}