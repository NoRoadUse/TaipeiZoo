package com.example.taipeizoo.datamodel


import com.google.gson.annotations.SerializedName

data class AnimalData(
    @SerializedName("result")
    val animalContent: AnimalContent? = AnimalContent()
)