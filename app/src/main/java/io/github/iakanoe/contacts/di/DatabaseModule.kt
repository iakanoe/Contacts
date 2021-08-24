package io.github.iakanoe.contacts.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.iakanoe.contacts.source.database.AppDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(appContext: Application) = Room.databaseBuilder(
        appContext,
        AppDatabase::class.java,
        "contacts"
    ).build()

    @Provides
    fun provideContactDao(appDatabase: AppDatabase) = appDatabase.provideContactDao()
}