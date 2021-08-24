package io.github.iakanoe.contacts.source.database

import androidx.room.Database
import androidx.room.RoomDatabase
import io.github.iakanoe.contacts.source.dao.ContactDao
import io.github.iakanoe.contacts.source.dto.ContactDto

@Database(entities = [ContactDto::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun provideContactDao(): ContactDao
}