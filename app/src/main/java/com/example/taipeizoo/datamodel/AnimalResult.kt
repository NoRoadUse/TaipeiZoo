package com.example.taipeizoo.datamodel


import com.google.gson.annotations.SerializedName

data class AnimalResult(
    @SerializedName("count")
    val count: Int? = 0,
    @SerializedName("limit")
    val limit: Int? = 0,
    @SerializedName("offset")
    val offset: Int? = 0,
    @SerializedName("results")
    val results: List<AnimalResultX>? = listOf(),
    @SerializedName("sort")
    val sort: String? = ""
)