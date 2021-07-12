package io.github.iakanoe.contacts.usecase

import io.github.iakanoe.contacts.domain.ContactRepository
import io.github.iakanoe.contacts.domain.model.Contact

class GetContactListUseCase constructor(private val contactRepository: ContactRepository) {
    suspend operator fun invoke(): List<Contact> = contactRepository.getContactList()
}