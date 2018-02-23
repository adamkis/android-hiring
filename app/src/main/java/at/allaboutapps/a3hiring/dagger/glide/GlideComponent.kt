package at.allaboutapps.a3hiring.dagger.glide

import at.allaboutapps.a3hiring.ui.adapter.SearchResultAdapter
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = arrayOf(
        GlideModule::class))
interface GlideComponent {
    fun inject(searchResultAdapter: SearchResultAdapter)
}