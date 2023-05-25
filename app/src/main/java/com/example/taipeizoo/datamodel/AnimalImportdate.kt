package com.example.taipeizoo.datamodel


import com.google.gson.annotations.SerializedName

data class AnimalImportdate(
    @SerializedName("date")
    val date: String? = "",
    @SerializedName("timezone")
    val timezone: String? = "",
    @SerializedName("timezone_type")
    val timezoneType: Int? = 0
)