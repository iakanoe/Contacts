package io.github.iakanoe.contacts.data

import io.github.iakanoe.contacts.domain.model.Contact

interface ContactLocalService {
    suspend fun getAll(): List<Contact>

    suspend fun insertAll(contacts: List<Contact>)

    suspend fun update(contact: Contact)
}