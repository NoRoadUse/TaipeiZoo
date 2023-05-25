package com.example.taipeizoo.datamodel


import com.google.gson.annotations.SerializedName

data class AnimalData(
    @SerializedName("result")
    val animalResult: AnimalResult? = AnimalResult()
)