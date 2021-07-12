package io.github.iakanoe.contacts.source.service

import io.github.iakanoe.contacts.data.ContactApiService
import io.github.iakanoe.contacts.domain.model.Contact
import io.github.iakanoe.contacts.source.api.ContactApi
import io.github.iakanoe.contacts.source.mapper.Mapper

class ContactApiServiceImpl(private val api: ContactApi) : ContactApiService {
    override suspend fun getContacts(): List<Contact> {
        return api.getContacts().map { Mapper.dtoToContact(it) }
    }
}