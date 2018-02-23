package at.allaboutapps.a3hiring.dagger.network

import at.allaboutapps.a3hiring.ui.fragment.SearchFragment
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = arrayOf(
        OkHttpModule::class,
        GsonConverterFactoryModule::class,
        LoggingInterceptorModule::class,
        RestApiModule::class,
        AppModule::class,
        RetrofitModule::class))
interface NetComponent {
    fun inject(searchFragment: SearchFragment)
}
