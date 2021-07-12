package io.github.iakanoe.contacts.source.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import io.github.iakanoe.contacts.source.dto.ContactDto

@Dao
interface ContactDao {
    @Query("SELECT * FROM contact")
    suspend fun getAll(): List<ContactDto>

    @Insert
    suspend fun insertAll(contacts: List<ContactDto>)

    @Update
    suspend fun update(contact: ContactDto)
}