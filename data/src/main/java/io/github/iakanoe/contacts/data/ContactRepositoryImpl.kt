package io.github.iakanoe.contacts.data

import io.github.iakanoe.contacts.domain.ContactRepository
import io.github.iakanoe.contacts.domain.model.Contact

class ContactRepositoryImpl constructor(
    private val apiService: ContactApiService, private val localService: ContactLocalService
) : ContactRepository {

    override suspend fun getContactList(): List<Contact> {
        var contacts = localService.getAll()

        if (contacts.isEmpty()) {
            contacts = apiService.getContacts()
            localService.insertAll(contacts)
        }

        return contacts
    }

    override suspend fun updateContact(contact: Contact) = localService.update(contact)
}