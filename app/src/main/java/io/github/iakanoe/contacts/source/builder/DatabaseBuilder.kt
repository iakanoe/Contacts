package io.github.iakanoe.contacts.source.builder

import android.content.Context
import androidx.room.Room
import io.github.iakanoe.contacts.source.database.AppDatabase

object DatabaseBuilder {
    fun provideAppDatabase(appContext: Context) = Room.databaseBuilder(
        appContext,
        AppDatabase::class.java,
        "contacts"
    ).build()
}