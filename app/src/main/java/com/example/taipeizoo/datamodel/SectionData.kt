package com.example.taipeizoo.datamodel


import com.google.gson.annotations.SerializedName

data class SectionData(
    @SerializedName("result")
    val result: SectionResult? = SectionResult()
)