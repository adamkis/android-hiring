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
        var builder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG){
            builder = builder.addInterceptor(httpLoggingInterceptor)
        }
        return builder.build()
    }
}