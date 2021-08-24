package io.github.iakanoe.contacts.source.service

import io.github.iakanoe.contacts.data.ContactLocalService
import io.github.iakanoe.contacts.domain.model.Contact
import io.github.iakanoe.contacts.source.dao.ContactDao
import io.github.iakanoe.contacts.source.mapper.Mapper
import javax.inject.Inject

class ContactLocalServiceImpl @Inject constructor(private val dao: ContactDao) : ContactLocalService {
    override suspend fun getAll(): List<Contact> = dao.getAll().map { Mapper.dtoToContact(it) }

    override suspend fun insertAll(contacts: List<Contact>) = dao.insertAll(contacts.map { Mapper.contactToDto(it) })

    override suspend fun update(contact: Contact) = dao.update(contact.let { Mapper.contactToDto(it) })
}