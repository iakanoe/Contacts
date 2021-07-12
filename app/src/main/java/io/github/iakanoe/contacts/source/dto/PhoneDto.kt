package io.github.iakanoe.contacts.source.dto

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Keep
data class PhoneDto(
    @SerializedName("work") val work: String?,
    @SerializedName("home") val home: String?,
    @SerializedName("mobile") val mobile: String?
) : Serializable