package com.example.rickandmorti.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.example.m5lesson4_retrofitmvvm_rickandmortyapi.data.room.CharacterDatabase
import com.example.rickandmorti.data.CartoonApiService
import com.example.rickandmorti.room.CharacterDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Provides
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient{
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient
    ): Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://rickandmortyapi.com/api/")
            .client(okHttpClient)// Передает OkHttpClient
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideApiService(
        retrofit: Retrofit
    ): CartoonApiService {
        return retrofit.create(CartoonApiService::class.java)
    }

    @Provides
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences("onboarding_prefs", Context.MODE_PRIVATE)
    }

    @Provides
    fun provideDatabase(@ApplicationContext context: Context): CharacterDatabase {
        return Room.databaseBuilder(
            context,
            CharacterDatabase::class.java,
            "rick_and_morty_database"
        )
            .fallbackToDestructiveMigration()// Добавь миграции, если есть
            .build()
    }

    @Provides
    fun provideCharacterDao(database: CharacterDatabase): CharacterDao {
        return database.charactersDao()
    }

    @Provides
    fun provideContext(@ApplicationContext context: Context): Context {
        return context
    }

}