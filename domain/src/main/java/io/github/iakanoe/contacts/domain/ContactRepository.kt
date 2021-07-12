package io.github.iakanoe.contacts.domain

import io.github.iakanoe.contacts.domain.model.Contact

interface ContactRepository {
    suspend fun getContactList(): List<Contact>

    suspend fun updateContact(contact: Contact)
}