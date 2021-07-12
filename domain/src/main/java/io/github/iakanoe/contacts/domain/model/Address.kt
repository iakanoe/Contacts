package io.github.iakanoe.contacts.domain.model

data class Address(
    val street: String,
    val city: String,
    val state: String,
    val country: String,
    val zipCode: String
) {
    fun toReadableString(): String {
        return listOf(street, city, state, country, zipCode).joinToString(", ")
    }
}