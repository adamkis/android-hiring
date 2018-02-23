package at.allaboutapps.a3hiring.dagger.network

import dagger.Module
import dagger.Provides
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
class GsonConverterFactoryModule {
    @Provides
    @Singleton
    fun provideGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }
}