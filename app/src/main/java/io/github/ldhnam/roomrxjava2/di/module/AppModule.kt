package io.github.ldhnam.roomrxjava2.di.module

import android.arch.persistence.room.Room
import dagger.Module
import dagger.Provides
import io.github.ldhnam.roomrxjava2.App
import io.github.ldhnam.roomrxjava2.common.Constants
import io.github.ldhnam.roomrxjava2.data.local.AppDatabase
import io.github.ldhnam.roomrxjava2.data.remote.RestService
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class AppModule(private val mApplication: App) {

    @Provides
    @Singleton
    fun provideApplication(): App {
        return mApplication
    }

    @Provides
    @Singleton
    fun provideCache(): Cache {
        val cacheSize = 10 * 1024 * 1024L
        return Cache(mApplication.cacheDir, cacheSize)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(cache: Cache): OkHttpClient {
        return OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .cache(cache)
                .build()
    }

    @Provides
    @Singleton
    fun provideRestService(okHttpClient: OkHttpClient): RestService {
        val retrofit = Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build()
        return retrofit.create(RestService::class.java)
    }

    @Provides
    @Singleton
    fun provideDatabase(): AppDatabase {
        return Room.databaseBuilder(mApplication, AppDatabase::class.java, "app-database").build()
    }
}