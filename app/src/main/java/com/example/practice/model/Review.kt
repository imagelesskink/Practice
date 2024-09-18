package com.example.practice.model


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Review(
    @SerializedName("comment")
    val comment: String? = "",
    @SerializedName("date")
    val date: String? = "",
    @SerializedName("rating")
    val rating: Int? = 0,
    @SerializedName("reviewerEmail")
    val reviewerEmail: String? = "",
    @SerializedName("reviewerName")
    val reviewerName: String? = ""
): Serializable