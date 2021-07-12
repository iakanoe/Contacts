package io.github.iakanoe.contacts.source.mapper

import io.github.iakanoe.contacts.domain.model.Address
import io.github.iakanoe.contacts.domain.model.Contact
import io.github.iakanoe.contacts.domain.model.Phone
import io.github.iakanoe.contacts.source.dto.AddressDto
import io.github.iakanoe.contacts.source.dto.ContactDto
import io.github.iakanoe.contacts.source.dto.PhoneDto

object Mapper {
    fun dtoToContact(dto: ContactDto) = Contact(
        name = dto.name,
        id = dto.id,
        companyName = dto.companyName,
        isFavorite = dto.isFavorite,
        smallImageURL = dto.smallImageURL,
        largeImageURL = dto.largeImageURL,
        emailAddress = dto.emailAddress,
        birthdate = dto.birthdate,
        phone = dtoToPhone(dto.phone),
        address = dtoToAddress(dto.address)
    )

    fun contactToDto(contact: Contact) = ContactDto(
        name = contact.name,
        id = contact.id,
        companyName = contact.companyName,
        isFavorite = contact.isFavorite,
        smallImageURL = contact.smallImageURL,
        largeImageURL = contact.largeImageURL,
        emailAddress = contact.emailAddress,
        birthdate = contact.birthdate,
        phone = phoneToDto(contact.phone),
        address = addressToDto(contact.address)
    )

    private fun dtoToAddress(dto: AddressDto) = Address(
        street = dto.street,
        city = dto.city,
        state = dto.state,
        country = dto.country,
        zipCode = dto.zipCode
    )

    private fun addressToDto(address: Address) = AddressDto(
        street = address.street,
        city = address.city,
        state = address.state,
        country = address.country,
        zipCode = address.zipCode
    )

    private fun dtoToPhone(dto: PhoneDto) = Phone(
        work = dto.work,
        home = dto.home,
        mobile = dto.mobile
    )

    private fun phoneToDto(phone: Phone) = PhoneDto(
        work = phone.work,
        home = phone.home,
        mobile = phone.mobile
    )
}