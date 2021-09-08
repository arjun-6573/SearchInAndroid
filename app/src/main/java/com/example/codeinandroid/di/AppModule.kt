package com.example.codeinandroid.di

import androidx.room.Room
import com.example.codeinandroid.BuildConfig
import com.example.codeinandroid.data.datasources.EventsLocalDataSourceImpl
import com.example.codeinandroid.data.datasources.EventsRemoteDataSourceImpl
import com.example.codeinandroid.data.mappers.EventsDataMapper
import com.example.codeinandroid.data.repositories.EventsRepositoryImpl
import com.example.codeinandroid.domain.datasources.EventsLocalDataSource
import com.example.codeinandroid.domain.datasources.EventsRemoteDataSource
import com.example.codeinandroid.domain.repositories.EventsRepository
import com.example.codeinandroid.domain.usecases.MarkAndUnMarkEventAsFavouriteUseCase
import com.example.codeinandroid.domain.usecases.SearchEventsUseCase
import com.example.codeinandroid.external.db.AppDatabase
import com.example.codeinandroid.external.remote.MyApi
import com.example.codeinandroid.external.remote.RequestInterceptor
import com.example.codeinandroid.utils.AppConstant
import com.example.codeinandroid.utils.dispatcher.MyDispatchers
import com.example.codeinandroid.utils.dispatcher.MyDispatchersImpl
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.util.concurrent.TimeUnit

val appModule = module {
    single<Interceptor> {
        HttpLoggingInterceptor {
            Timber.tag("OK-HTTP").i(it)
        }.setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    single { GsonBuilder().create() }

    single {
        OkHttpClient.Builder()
            .addInterceptor(RequestInterceptor())
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(get<Interceptor>())
            .build()
    }

    single<MyApi> {
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(get()))
            .client(get())
            .build().run {
                create(MyApi::class.java)
            }
    }

    single {
        Room.databaseBuilder(
            androidApplication(),
            AppDatabase::class.java, AppConstant.DB_NAME
        ).build()
    }

    single<MyDispatchers> { MyDispatchersImpl() }
    single<EventsRemoteDataSource> { EventsRemoteDataSourceImpl(get(), get(), get()) }
    single<EventsLocalDataSource> { EventsLocalDataSourceImpl(get(), get(), get()) }
    single<EventsRepository> { EventsRepositoryImpl(get(), get()) }
    single { EventsDataMapper() }
    factory { MarkAndUnMarkEventAsFavouriteUseCase(get()) }
    factory { SearchEventsUseCase(get()) }
}