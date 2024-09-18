package com.example.practice.model


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Meta(
    @SerializedName("barcode")
    val barcode: String? = "",
    @SerializedName("createdAt")
    val createdAt: String? = "",
    @SerializedName("qrCode")
    val qrCode: String? = "",
    @SerializedName("updatedAt")
    val updatedAt: String? = ""
): Serializable