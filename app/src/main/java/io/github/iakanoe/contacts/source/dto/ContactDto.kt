package io.github.iakanoe.contacts.source.dto

import androidx.annotation.Keep
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "contact")
@Keep
data class ContactDto(
    @SerializedName("name") val name: String,
    @SerializedName("id") @PrimaryKey val id: Int,
    @SerializedName("companyName") val companyName: String?,
    @SerializedName("isFavorite") var isFavorite: Boolean,
    @SerializedName("smallImageURL") val smallImageURL: String,
    @SerializedName("largeImageURL") val largeImageURL: String,
    @SerializedName("emailAddress") val emailAddress: String,
    @SerializedName("birthdate") val birthdate: String,
    @SerializedName("phone") @Embedded val phone: PhoneDto,
    @SerializedName("address") @Embedded val address: AddressDto
) : Serializable