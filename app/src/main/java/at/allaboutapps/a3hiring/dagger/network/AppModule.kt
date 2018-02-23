package at.allaboutapps.a3hiring.dagger.network

import android.app.Application
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private var mApplication: Application) {

    @Provides
    @Singleton
    fun providesApplication(): Application {
        return mApplication
    }

}
