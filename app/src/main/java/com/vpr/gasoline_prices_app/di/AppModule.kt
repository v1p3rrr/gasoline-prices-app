package com.vpr.gasoline_prices_app.di

import android.content.Context
import androidx.room.Room
import com.vpr.gasoline_prices_app.data.local.GasolinePriceDao
import com.vpr.gasoline_prices_app.data.local.GasolinePriceDatabase
import com.vpr.gasoline_prices_app.data.remote.GasolinePricesService
import com.vpr.gasoline_prices_app.data.repository.GasolinePriceRepositoryImpl
import com.vpr.gasoline_prices_app.domain.repository.GasolinePriceRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.ConnectionSpec
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.Arrays
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    private const val BASE_URL = "http://10.0.2.2:8080/"

    @Singleton
    @Provides
    fun provideGasolinePriceRepository(service: GasolinePricesService, dao: GasolinePriceDao): GasolinePriceRepository = GasolinePriceRepositoryImpl(api = service, dao = dao)

    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .connectionSpecs(listOf(ConnectionSpec.CLEARTEXT, ConnectionSpec.MODERN_TLS))
            .build()

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Singleton
    @Provides
    fun provideGasolinePricesService(retrofit: Retrofit): GasolinePricesService = retrofit.create(GasolinePricesService::class.java)

    @Singleton
    @Provides
    fun provideGasolinePriceDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(
            context.applicationContext,
            GasolinePriceDatabase::class.java,
            "gasoline_price_database"
        ).build()

    @Singleton
    @Provides
    fun provideGasolinePriceDao(database: GasolinePriceDatabase) = database.gasolinePriceDao()

}