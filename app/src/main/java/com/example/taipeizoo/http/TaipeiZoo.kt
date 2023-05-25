package com.example.taipeizoo.http

import com.example.taipeizoo.datamodel.ResponseAnimal
import com.example.taipeizoo.datamodel.SectionData
import retrofit2.http.GET

interface TaipeiZoo {
    @GET("https://data.taipei/api/v1/dataset/5a0e5fbb-72f8-41c6-908e-2fb25eff9b8a?scope=resourceAquire")
    suspend fun getZooSectionIntro(): SectionData

    @GET("https://data.taipei/api/v1/dataset/a3e2b221-75e0-45c1-8f97-75acbd43d613?scope=resourceAquire")
    suspend fun getAnimalsInfo(): ResponseAnimal
}