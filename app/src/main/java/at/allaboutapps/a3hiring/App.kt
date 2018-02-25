package at.allaboutapps.a3hiring

import android.app.Application
import android.content.Context
import android.support.annotation.VisibleForTesting
import at.allaboutapps.a3hiring.api.RestApi
import at.allaboutapps.a3hiring.dagger.glide.DaggerGlideComponent
import at.allaboutapps.a3hiring.dagger.glide.GlideComponent
import at.allaboutapps.a3hiring.dagger.glide.GlideModule
import at.allaboutapps.a3hiring.dagger.network.*
import com.squareup.leakcanary.LeakCanary
import io.paperdb.Paper
import timber.log.Timber

/**
 * Created by adam on 2018. 02. 23..
 */


class App : Application() {

    @VisibleForTesting
    companion object {
        lateinit var netComponent: NetComponent
        lateinit var glideComponent: GlideComponent
    }

    fun setNetComponent(netComponent: NetComponent){
        App.netComponent = netComponent
    }

    fun createNetComponent(baseUrl: String): NetComponent {
        return DaggerNetComponent.builder()
                .okHttpModule(OkHttpModule())
                .gsonConverterFactoryModule(GsonConverterFactoryModule())
                .loggingInterceptorModule(LoggingInterceptorModule())
                .restApiModule(RestApiModule())
                .retrofitModule(RetrofitModule(baseUrl))
                .appModule(AppModule(this))
                .build()
    }

    fun createGlideComponent(context: Context): GlideComponent {
        return DaggerGlideComponent.builder()
                .glideModule(GlideModule(context))
                .build()
    }

    override fun onCreate() {
        super.onCreate()
        netComponent = createNetComponent(RestApi.A3_URL_BASE)
        glideComponent = createGlideComponent(this)
        // Timber
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        Timber.tag("A3")
        // LeakCanary
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return
        }
        LeakCanary.install(this)
        // Paper
        Paper.init(applicationContext)
    }

}