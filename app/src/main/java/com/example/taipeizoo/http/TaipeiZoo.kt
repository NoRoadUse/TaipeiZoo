package com.example.taipeizoo.http

import com.example.taipeizoo.datamodel.AnimalData
import com.example.taipeizoo.datamodel.SectionData
import retrofit2.Response
import retrofit2.http.GET

interface TaipeiZoo {

    @GET("5a0e5fbb-72f8-41c6-908e-2fb25eff9b8a?scope=resourceAquire")
    suspend fun getZooSectionIntro(): Result<SectionData>

    @GET("a3e2b221-75e0-45c1-8f97-75acbd43d613?scope=resourceAquire")
    suspend fun getAnimalsInfo(): Result<AnimalData>
}