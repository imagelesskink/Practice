package com.example.practice.model


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Dimensions(
    @SerializedName("depth")
    val depth: Double? = 0.0,
    @SerializedName("height")
    val height: Double? = 0.0,
    @SerializedName("width")
    val width: Double? = 0.0
): Serializable