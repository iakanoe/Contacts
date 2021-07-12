package io.github.iakanoe.contacts.data

import io.github.iakanoe.contacts.domain.model.Contact

interface ContactApiService {
    suspend fun getContacts(): List<Contact>
}