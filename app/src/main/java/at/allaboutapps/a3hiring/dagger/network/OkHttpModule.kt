package at.allaboutapps.a3hiring.dagger.network

import android.app.Application
import at.allaboutapps.a3hiring.BuildConfig
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Singleton


@Module
class OkHttpModule {
    @Provides
    @Singleton
    fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor,
                            application: Application
        ): OkHttpClient {
        val cacheSize: Long = 10 * 1024 * 1024 // 10 MB
        val cache = Cache(application.cacheDir, cacheSize)
        var builder = OkHttpClient.Builder()
                .cache(cache)
        if (BuildConfig.DEBUG){
            builder = builder.addInterceptor(httpLoggingInterceptor)
        }
        return builder.build()
    }
}