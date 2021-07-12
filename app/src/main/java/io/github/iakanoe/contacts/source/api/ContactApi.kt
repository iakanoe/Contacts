package io.github.iakanoe.contacts.source.api

import io.github.iakanoe.contacts.source.dto.ContactDto
import retrofit2.http.GET

interface ContactApi {
    @GET("contacts.json")
    suspend fun getContacts(): List<ContactDto>
}