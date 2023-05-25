package com.example.taipeizoo.datamodel


import com.google.gson.annotations.SerializedName

data class SectionResult(
    @SerializedName("count")
    val count: Int? = 0,
    @SerializedName("limit")
    val limit: Int? = 0,
    @SerializedName("offset")
    val offset: Int? = 0,
    @SerializedName("results")
    val results: List<SectionResultX>? = listOf(),
    @SerializedName("sort")
    val sort: String? = ""
)