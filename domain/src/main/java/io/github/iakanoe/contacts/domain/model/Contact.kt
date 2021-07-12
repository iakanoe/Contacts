package io.github.iakanoe.contacts.domain.model

data class Contact(
    val name: String,
    val id: Int,
    val companyName: String?,
    var isFavorite: Boolean,
    val smallImageURL: String,
    val largeImageURL: String,
    val emailAddress: String,
    val birthdate: String,
    val phone: Phone,
    val address: Address
)